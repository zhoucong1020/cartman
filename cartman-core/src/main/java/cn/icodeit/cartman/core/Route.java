package cn.icodeit.cartman.core;

import cn.icodeit.cartman.core.server.Request;
import cn.icodeit.cartman.core.server.Response;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhoucong
 */
public interface Route {

    void handle(Request request, Response response);
}
