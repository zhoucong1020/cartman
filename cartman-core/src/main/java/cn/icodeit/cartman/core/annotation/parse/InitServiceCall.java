package cn.icodeit.cartman.core.annotation.parse;

import cn.icodeit.cartman.core.annotation.mode.AccessElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * .
 * User: yuanweifeng
 * Date: 14-11-19
 * Time: 下午2:25
 */
public class InitServiceCall {

    private static Map<String, AccessElement> requestLocation = new HashMap<>();

    private static List<String> excludeKeys = new ArrayList<>();


    public static void put(String key, AccessElement value) {
        requestLocation.put(key, value);
    }


    public static boolean isContainKey(String key) {
        return requestLocation.get(key) != null;
    }

    private static void unDefineRequestCheck(String key) {
        if (!isExcludeKey(key) && !isContainKey(key)) {
            throw new IllegalAccessError("unDefine request :" + key);
        }
    }

    private static void excludeRequestCheck(String key) {
        if (isExcludeKey(key)) {
            throw new IllegalArgumentException("exclude request :" + key);
        }
    }

    public static AccessElement get(String key)
            throws IllegalAccessError, IllegalArgumentException {
        excludeRequestCheck(key);
        unDefineRequestCheck(key);

        return requestLocation.get(key);

    }

    public static String createKey(String serviceName, String mappingName) {
        String fullKey = "/" + serviceName + "/" + mappingName;
        return fullKey.trim();
    }

    public static void addExcludeKey(String key) {
        if (!isExcludeKey(key)) {
            excludeKeys.add(key);
        }
    }

    public static boolean isExcludeKey(String key) {
        return excludeKeys.contains(key);
    }

}
