package cn.icodeit.cartman.core.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhoucong
 */
public enum CartmanUtils {
    ;

    public static List<String> convertRouteToList(String route) {
        String[] pathArray = route.split("/");
        List<String> path = new ArrayList<String>();
        for (String p : pathArray) {
            if (p.length() > 0) {
                path.add(p);
            }
        }
        return path;
    }
}
