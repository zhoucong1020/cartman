package cn.icodeit.cartman.core.route;

import cn.icodeit.cartman.core.Route;
import cn.icodeit.cartman.core.annotation.RequestMethod;
import cn.icodeit.cartman.core.utils.CartmanUtils;

import java.util.List;

/**
 * @author zhoucong
 * @since 0.0.1
 */
public class RouteEntry {

    public String path;
    public RequestMethod requestMethod;
    public Route target;

    public boolean match(String path, RequestMethod requestMethod) {
        if (this.requestMethod == requestMethod) {
            return matchPath(path);
        }

        return false;
    }

    public boolean matchPath(String path) {
        if (!this.path.endsWith("*") && ((path.endsWith("/") && !this.path.endsWith("/"))
                || (this.path.endsWith("/") && !path.endsWith("/")))) {
            return false;
        }
        if (this.path.equals(path)) {
            return true;
        }

        List<String> thisPathList = CartmanUtils.convertRouteToList(this.path);
        List<String> pathList = CartmanUtils.convertRouteToList(path);

        int thisPathSize = thisPathList.size();
        int pathSize = pathList.size();

        if (thisPathSize == pathSize) {
            for (int i = 0; i < thisPathSize; i++) {
                String thisPathPart = thisPathList.get(i);
                String pathPart = pathList.get(i);

                if ((i == thisPathSize - 1) && (thisPathPart.equals("*") && this.path.endsWith("*"))) {
                    return true;
                }

                if ((!thisPathPart.startsWith(":"))
                        && !thisPathPart.equals(pathPart)
                        && !thisPathPart.equals("*")) {
                    return false;
                }
            }
            return true;
        } else {
            if (this.path.endsWith("*")) {
                if (pathSize == (thisPathSize - 1) && (path.endsWith("/"))) {
                    pathList.add("");
                    pathList.add("");
                    pathSize += 2;
                }

                if (thisPathSize < pathSize) {
                    for (int i = 0; i < thisPathSize; i++) {
                        String thisPathPart = thisPathList.get(i);
                        String pathPart = pathList.get(i);
                        if (thisPathPart.equals("*") && (i == thisPathSize - 1) && this.path.endsWith("*")) {
                            return true;
                        }
                        if (!thisPathPart.startsWith(":")
                                && !thisPathPart.equals(pathPart)
                                && !thisPathPart.equals("*")) {
                            return false;
                        }
                    }
                    return true;
                }
            }
            return false;
        }
    }
}
