package cn.icodeit.cartman.core.route;

import cn.icodeit.cartman.core.io.Request;
import cn.icodeit.cartman.core.io.Response;

/**
 * @author zhoucong
 */
public interface Route {

    Object handle(Request request, Response response);
}
