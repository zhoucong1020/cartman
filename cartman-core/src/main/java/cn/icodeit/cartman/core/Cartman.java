package cn.icodeit.cartman.core;

import cn.icodeit.cartman.core.io.AbstractHandler;
import cn.icodeit.cartman.core.io.CartmanServer;

import javax.net.ssl.SSLException;
import java.security.cert.CertificateException;

import static cn.icodeit.cartman.core.io.HandlerContainer.putHandler;

/**
 * @author zhoucong
 */
public class Cartman {

    private static volatile boolean initialized = false;

    public synchronized static void handler(String path, AbstractHandler handler) {
        init();
        putHandler(path, handler);
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
