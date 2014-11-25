package cn.icodeit.cartman.core.io;

import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;

import java.nio.charset.Charset;

/**
 * @author zhoucong
 */
public class Response {

    private FullHttpResponse response;
    private String body;

    protected Response() {
    }

    public Response(FullHttpResponse response) {
        this.response = response;
    }

    /**
     * @param statusCode 状态码
     */
    public void status(int statusCode) {
        response.setStatus(HttpResponseStatus.valueOf(statusCode));
    }

    /**
     * @param contentType Content-Type
     */
    public void type(String contentType) {
        response.headers().add("Content-Type", contentType);
    }

    /**
     * @param body 默认以utf-8编码写入内容
     */
    public void body(String body) {
        this.body = body;
        response.content().writeBytes(Charset.defaultCharset().encode(body));
        response.headers().add("Content-Length", response.content().readableBytes());
    }

    /**
     * @return body
     */
    public String body() {
        return this.body;
    }
}
