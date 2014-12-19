package cn.icodeit.cartman.core.server;

import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;

import static io.netty.handler.codec.http.HttpResponseStatus.CONTINUE;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * @author zhoucong
 * @since 0.0.1
 */
public class CartmanServerRequestHandler extends ChannelInboundHandlerAdapter {

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

        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
