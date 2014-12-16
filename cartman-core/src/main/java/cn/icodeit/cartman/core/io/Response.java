package cn.icodeit.cartman.core.io;

import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;

import java.nio.charset.Charset;

import static io.netty.handler.codec.http.HttpHeaders.Names.ACCESS_CONTROL_ALLOW_HEADERS;
import static io.netty.handler.codec.http.HttpHeaders.Names.ACCESS_CONTROL_ALLOW_ORIGIN;

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
        try {
            response.content().writeBytes(Charset.forName("UTF-8").encode(body));
            response.headers().add("Content-Length", response.content().readableBytes());
            response.headers().add("charset", "utf-8");
            response.headers().set(ACCESS_CONTROL_ALLOW_ORIGIN,"*");
            response.headers().set(ACCESS_CONTROL_ALLOW_HEADERS,"content-type");
        } catch (Exception exp) {
            exp.printStackTrace();
        }
    }
}
