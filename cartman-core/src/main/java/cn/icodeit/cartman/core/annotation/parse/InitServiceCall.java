package cn.icodeit.cartman.core.annotation.parse;

import cn.icodeit.cartman.core.annotation.mode.AccessElement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * .
 * User: yuanweifeng
 * Date: 14-11-19
 * Time: 下午2:25
 */
public class InitServiceCall {

    private static Map<String, AccessElement> requestLocation = new HashMap<>();


    public static void put(String key, AccessElement value) {
        requestLocation.put(key, value);
    }

    public static boolean isContainKey(String key) {
        return get(key) != null;
    }

    public static AccessElement get(String key) {
        return requestLocation.get(key);
    }

    public static String createKey(String serviceName, String mappingName) {
        return "/" + serviceName + "/" + mappingName;
    }


    public static Set<String> toStrings() {
         return requestLocation.keySet();

    }
}
