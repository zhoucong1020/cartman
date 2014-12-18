package cn.icodeit.cartman.core.io;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhoucong
 */
public class HandlerContainer {

    private static Map<String, AbstractHandler> handlerMap = new HashMap<>();

    public static void putHandler(String path, AbstractHandler handler) {
        if (handlerMap.containsKey(path)) {
            throw new PathAlreadyMappedException(path);
        }
        handlerMap.put(path, handler);
    }

    /*
    TODO 增加匹配模式
    考虑使用状态机实现
    */
    public static AbstractHandler getHandler(String path) {
        for (String pattern : handlerMap.keySet()) {
            if (path.startsWith(pattern)) {
                return handlerMap.get(pattern);
            }
        }

        return null;
    }
}
