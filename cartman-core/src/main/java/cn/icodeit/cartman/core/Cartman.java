package cn.icodeit.cartman.core;

import cn.icodeit.cartman.core.io.server.CartmanServer;
import cn.icodeit.cartman.core.utils.ClassMapper;
import cn.icodeit.cartman.core.utils.ClassScanner;
import cn.icodeit.cartman.core.service.annotation.RequestMethod;
import cn.icodeit.cartman.core.service.mapping.ActionMapping;

import javax.net.ssl.SSLException;
import java.security.cert.CertificateException;

/**
 * @author zhoucong
 */
public class Cartman {

    private static volatile boolean initialized = false;
    private static ClassMapper classMapper = new ClassMapper();

    public synchronized static void start() {
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

    public synchronized static void stop() {
        if (initialized) {

        }
    }

    public static void scan(String packageName) {
        ClassScanner.scan(packageName).forEach(classMapper::mapClass);
    }

    public static ActionMapping getMapping(String uri, RequestMethod requestMethod) {
        return classMapper.get(uri, requestMethod);
    }
}
