package cn.icodeit.cartman.core.route;

import cn.icodeit.cartman.core.Route;
import cn.icodeit.cartman.core.annotation.RequestMethod;

/**
 * @author zhoucong
 */
public class RouteMatch {

    private RequestMethod requestMethod;
    private Route target;
    private String matchUri;
    private String requestURI;

    public RouteMatch(RequestMethod requestMethod, Route target, String matchUri, String requestUri) {
        super();
        this.requestMethod = requestMethod;
        this.target = target;
        this.matchUri = matchUri;
        this.requestURI = requestUri;
    }

    public RequestMethod getRequestMethod() {
        return requestMethod;
    }

    public Route getTarget() {
        return target;
    }

    public String getMatchUri() {
        return matchUri;
    }

    public String getRequestURI() {
        return requestURI;
    }
}
