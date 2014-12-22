package cn.icodeit.cartman.core.server;

import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaders;

import static io.netty.handler.codec.http.HttpHeaders.Names.CONNECTION;
import static io.netty.handler.codec.http.HttpResponseStatus.CONTINUE;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * @author zhoucong
 * @since 0.0.1
 */
public class CartmanServerRequestHandler extends ChannelInboundHandlerAdapter {

    private MatchHandler matchHandler = new MatchHandler();

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
                ctx.writeAndFlush(new DefaultFullHttpResponse(HTTP_1_1, CONTINUE)).addListener(ChannelFutureListener.CLOSE);
                return;
            }

            //handle
            matchHandler.handle(new Request(request), new Response(response));

            //response
            boolean keepAlive = HttpHeaders.isKeepAlive(request);
            if (!keepAlive) {
                ctx.write(response).addListener(ChannelFutureListener.CLOSE);
            } else {
                response.headers().set(CONNECTION, HttpHeaders.Values.KEEP_ALIVE);
                ctx.write(response);
            }
            ctx.flush();
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
