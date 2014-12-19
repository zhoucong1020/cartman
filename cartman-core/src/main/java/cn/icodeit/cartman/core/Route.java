package cn.icodeit.cartman.core;

import cn.icodeit.cartman.core.server.Request;
import cn.icodeit.cartman.core.server.Response;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhoucong
 */
public abstract class Route {

    private String path;
    private Class<?> clazz;
    private Method method;
    private List<ParamImpl> params = new ArrayList<>();

    public Route(String path, Class<?> clazz, Method method) {
        this.path = path;
        this.clazz = clazz;
        this.method = method;
    }

    public abstract void handle(Request request, Response response);

    public String getPath() {
        return path;
    }

    public void addParam(ParamImpl param) {
        params.add(param);
    }
}
