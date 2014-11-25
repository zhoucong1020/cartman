package cn.icodeit.cartman.core.io;

import cn.icodeit.cartman.core.io.exception.PathAlreadyMappedException;
import cn.icodeit.cartman.core.io.server.CartmanServer;

import javax.net.ssl.SSLException;
import java.security.cert.CertificateException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhoucong
 */
public class Cartman {

    private static volatile boolean initialized = false;
    private static Map<String, Handler> handlerMappings = new HashMap<>();

    public synchronized static void addHandler(String path, Handler handler) {
        init();
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

    private static void init() {
        if (!initialized) {
            new Thread(() -> {
                try {
                    new CartmanServer().start();
                } catch (CertificateException | SSLException e) {
                    e.printStackTrace();
                }
            }).start();

            initialized = true;
        }
    }
}
