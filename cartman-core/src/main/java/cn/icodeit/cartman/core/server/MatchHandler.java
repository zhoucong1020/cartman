package cn.icodeit.cartman.core.server;

import cn.icodeit.cartman.core.annotation.RequestMethod;
import cn.icodeit.cartman.core.route.RouteMatch;
import cn.icodeit.cartman.core.route.RouteMatcher;
import cn.icodeit.cartman.core.route.RouteMatcherFactory;

/**
 * @author zhoucong
 * @since 0.0.1
 */
public class MatchHandler {

    public static final String NOT_FOUND_PAGE = "<html><body><h2>404 Not found</h2></body></html>";

    private RouteMatcher routeMatcher = RouteMatcherFactory.get();

    public void handle(Request request, Response response) {
        RequestMethod requestMethod = RequestMethod.valueOf(request.requestMethod().toUpperCase());
        RouteMatch route = routeMatcher.findTargetForRequestedRoute(request.url(), requestMethod);
        if (route == null) {
            response.status(404);
            response.body(NOT_FOUND_PAGE);
            return;
        }

        route.getTarget().handle(request, response);
    }
}
