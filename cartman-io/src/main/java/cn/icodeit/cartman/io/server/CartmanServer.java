package cn.icodeit.cartman.io.server;

import cn.icodeit.cartman.io.Handler;
import cn.icodeit.cartman.io.exception.PathAlreadyMappedException;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhoucong
 */
public class CartmanServer {

    public static Map<String, Handler> handlerMappings = new HashMap<>();

    public static void addHandler(String path, Handler handler) {
        if (handlerMappings.containsKey(path)) {
            throw new PathAlreadyMappedException(path);
        }

        handlerMappings.put(path, handler);
    }

    /*
    TODO 增加匹配模式
    考虑使用状态机实现
    */
    public static Handler mapHandler(String path) {
        for (String pattern : handlerMappings.keySet()) {
            if (path.startsWith(pattern)) {
                return handlerMappings.get(pattern);
            }
        }

        return null;
    }
}
