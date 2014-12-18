package cn.icodeit.cartman.core.io;

import io.netty.channel.ChannelHandlerContext;

/**
 * @author zhoucong
 */
public class ActionContext {

    private Request request;
    private Response response;

    private ChannelHandlerContext nettyContext;

    public Request request() {
        return request;
    }

    public void request(Request request) {
        this.request = request;
    }

    public Response response() {
        return response;
    }

    public void response(Response response) {
        this.response = response;
    }

    public void nettyContext(ChannelHandlerContext nettyContext) {
        this.nettyContext = nettyContext;
    }
}
