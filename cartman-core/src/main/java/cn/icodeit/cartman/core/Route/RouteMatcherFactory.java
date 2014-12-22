package cn.icodeit.cartman.core.route;

/**
 * @author zhoucong
 * @since 0.0.1
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
