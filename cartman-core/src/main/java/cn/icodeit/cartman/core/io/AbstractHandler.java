package cn.icodeit.cartman.core.io;

import cn.icodeit.cartman.core.io.Handler;
import cn.icodeit.cartman.core.io.Request;
import cn.icodeit.cartman.core.io.Response;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpHeaders;

import static io.netty.handler.codec.http.HttpHeaders.Names.CONNECTION;

/**
 * @author zhoucong
 */
public abstract class AbstractHandler implements Handler {

    public void doHandle(Request request, Response response, ChannelHandlerContext ctx) {
        handle(request, response);

        //keepAlive
        boolean keepAlive = HttpHeaders.isKeepAlive(request.request());
        if (!keepAlive) {
            ctx.write(response.response()).addListener(ChannelFutureListener.CLOSE);
        } else {
            response.response().headers().set(CONNECTION, HttpHeaders.Values.KEEP_ALIVE);
            ctx.write(response.response());
        }
        ctx.flush();

        System.out.println(request.requestMethod() + " " + request.uri() + " " + response.status() + " ");
    }
}
