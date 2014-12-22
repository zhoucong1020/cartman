package cn.icodeit.cartman.core.utils;

import java.io.PrintWriter;
import java.io.StringWriter;
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

    public static String getExceptionTrack(Exception e) {
        if (e != null) {
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            e.printStackTrace(printWriter);
            return stringWriter.toString();
        }

        return "";
    }

    public static boolean isWrapClass(Class clz) {
        try {
            return ((Class) clz.getField("TYPE").get(null)).isPrimitive();
        } catch (Exception e) {
            return false;
        }
    }
}
