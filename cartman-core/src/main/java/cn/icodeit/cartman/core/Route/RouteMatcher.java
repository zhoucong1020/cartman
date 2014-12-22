package cn.icodeit.cartman.core.route;

import cn.icodeit.cartman.core.Route;
import cn.icodeit.cartman.core.annotation.RequestMethod;

/**
 * @author zhoucong
 */
public interface RouteMatcher {

    void addRoute(String url, RequestMethod requestMethod, Route target);

    RouteMatch findTargetForRequestedRoute(String url, RequestMethod requestMethod);
}
