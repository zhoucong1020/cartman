package cn.icodeit.cartman.core.route;

import cn.icodeit.cartman.core.Route;
import cn.icodeit.cartman.core.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhoucong
 * @since 0.0.1
 */
public class SimpleRouteMatcher implements cn.icodeit.cartman.core.route.RouteMatcher {

    private List<RouteEntry> routeEntries;

    public SimpleRouteMatcher() {
        routeEntries = new ArrayList<>();
    }

    @Override
    public void addRoute(String url, RequestMethod requestMethod, Route target) {
        RouteEntry entry = new RouteEntry();
        entry.requestMethod = requestMethod;
        entry.path = url;
        entry.target = target;

        routeEntries.add(entry);
    }

    //TODO: ->ywf 考虑多匹配情况下的处理，考察其他框架的处理方式，提出方案，暂不修改
    @Override
    public RouteMatch findTargetForRequestedRoute(String url, RequestMethod requestMethod) {
        List<RouteEntry> matches = findTargetsForRequestedRoute(url, requestMethod);
        if (matches.size() > 0) {
            RouteEntry routeEntry = matches.get(0);
            return new RouteMatch(requestMethod, routeEntry.target, routeEntry.path, url);
        }

        return null;
    }

    private List<RouteEntry> findTargetsForRequestedRoute(String url, RequestMethod requestMethod) {
        List<RouteEntry> matches = new ArrayList<RouteEntry>();
        routeEntries.forEach(route -> {
            if (route.match(url, requestMethod)) {
                matches.add(route);
            }
        });

        return matches;
    }
}
