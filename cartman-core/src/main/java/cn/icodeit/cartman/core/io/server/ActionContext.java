package cn.icodeit.cartman.core.io.server;

import cn.icodeit.cartman.core.io.Request;
import cn.icodeit.cartman.core.io.Response;
import cn.icodeit.cartman.core.service.mapping.ActionMapping;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpHeaders;

import static io.netty.handler.codec.http.HttpHeaders.Names.CONNECTION;

/**
 * @author zhoucong
 * @since 0.0.1
 */
public class ActionContext {

    private Request request;
    private Response response;

    private ActionMapping mapping;
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

    public ActionMapping mapping() {
        return mapping;
    }

    public void mapping(ActionMapping mapping) {
        this.mapping = mapping;
    }

    public ChannelHandlerContext nettyContext() {
        return nettyContext;
    }

    public void nettyContext(ChannelHandlerContext nettyContext) {
        this.nettyContext = nettyContext;
    }

    public void flush() {
        boolean keepAlive = HttpHeaders.isKeepAlive(request.request());
        if (!keepAlive) {
            nettyContext.write(response.response()).addListener(ChannelFutureListener.CLOSE);
        } else {
            response.response().headers().set(CONNECTION, HttpHeaders.Values.KEEP_ALIVE);
            nettyContext.write(response.response());
        }
        nettyContext.flush();
        System.out.println(request.request().getMethod().name() + " " + request.request().getUri() + " " + response.response().getStatus().code() + " ");
    }
}
