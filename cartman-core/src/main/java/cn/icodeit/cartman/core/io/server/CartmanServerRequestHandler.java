package cn.icodeit.cartman.core.io.server;

import cn.icodeit.cartman.core.io.Request;
import cn.icodeit.cartman.core.io.Response;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaders;

import static io.netty.handler.codec.http.HttpResponseStatus.CONTINUE;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * @author zhoucong
 * @since 0.0.1
 */
public abstract class CartmanServerRequestHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        if (msg instanceof FullHttpRequest) {
            FullHttpRequest request = (FullHttpRequest) msg;
            FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, OK);

            //100 continue
            if (HttpHeaders.is100ContinueExpected(request)) {
                ctx.write(new DefaultFullHttpResponse(HTTP_1_1, CONTINUE));
            }

            //handle
            handle(createContext(request, response, ctx));
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

    public abstract void handle(ActionContext context);

    private ActionContext createContext(FullHttpRequest request, FullHttpResponse response, ChannelHandlerContext ctx) {
        ActionContext context = new ActionContext();
        context.request(new Request(request));
        context.response(new Response(response));
        context.nettyContext(ctx);

        return context;
    }
}
