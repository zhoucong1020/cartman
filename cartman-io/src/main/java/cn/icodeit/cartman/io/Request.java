package cn.icodeit.cartman.io;

import io.netty.handler.codec.http.FullHttpRequest;

import java.nio.charset.Charset;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhoucong
 * @since 0.0.1
 */
public class Request {

    private FullHttpRequest request;
    private Map<String, String> params;

    protected Request() {
    }

    public Request(FullHttpRequest request) {
        this.request = request;
        this.params = getParams();
    }

    /**
     * @return 默认以utf-8形式解码的body部分
     */
    public String body()
    {
        return request.content().toString(Charset.defaultCharset());
    }

    /**
     * @return Content-Length
     */
    public int contentLength() {
        return Integer.parseInt(request.headers().get("Content-Length"));
    }

    /**
     * @return Content-Type
     */
    public String contentType() {
        return request.headers().get("Content-Type");
    }

    /**
     * @param attribute 参数名
     * @return 参数值
     */
    public String attribute(String attribute) {
        return params.get(attribute);
    }

    /**
     * @return 请求URI部分
     */
    public String uri() {
        return request.getUri().split("\\?")[0];
    }

    /**
     * @return 请求方法
     */
    public String requestMethod() {
        return request.getMethod().name();
    }

    private Map<String, String> getParams() {
        Map<String, String> params = new HashMap<>();

        String[] uParam = request.getUri().split("\\?");
        if (uParam.length == 2) {
            for (String param : uParam[1].split("&")) {
                String[] strings = param.split("=");
                params.put(strings[0], strings[1]);
            }
        }

        String[] cParam = body().split("&");
        if (cParam.length > 0) {
            for (String param : cParam) {
                String[] strings = param.split("=");
                params.put(strings[0], strings[1]);
            }
        }

        return Collections.unmodifiableMap(params);
    }
}
