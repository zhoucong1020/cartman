package cn.icodeit.cartman.core.route;

import cn.icodeit.cartman.core.Route;
import cn.icodeit.cartman.core.annotation.RequestMethod;

/**
 * @author zhoucong
 * @since 0.0.1
 */
public interface RouteMatcher {

    void addRoute(String url, RequestMethod requestMethod, Route target);

    RouteMatch findTargetForRequestedRoute(String url, RequestMethod requestMethod);
}
