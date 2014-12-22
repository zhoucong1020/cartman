package cn.icodeit.cartman.core.route;

/**
 * @author zhoucong
 */
public enum RouteMatcherFactory {
    ;

    private static SimpleRouteMatcher routeMatcher = null;

    public static synchronized SimpleRouteMatcher get() {
        if (routeMatcher == null) {
            routeMatcher = new SimpleRouteMatcher();
        }
        return routeMatcher;
    }
}
