package cn.icodeit.cartman.core;

import cn.icodeit.cartman.core.server.Request;
import cn.icodeit.cartman.core.server.Response;

/**
 * @author zhoucong
 * @since 0.0.2
 */
public abstract class RouteImpl implements Route {

    private String path;

    protected RouteImpl(String path) {
        this.path = path;
    }

    public abstract void handle(Request request, Response response);
    public abstract void addParam(ParamImpl param);

    String getPath() {
        return this.path;
    }
}
