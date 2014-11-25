package cn.icodeit.cartman.core.boot;

import cn.icodeit.cartman.core.io.server.CartmanServer;

import javax.net.ssl.SSLException;
import java.security.cert.CertificateException;

import static cn.icodeit.cartman.core.io.HandlerMapping.addHandler;

/**
 * @author zhoucong
 * @since 0.0.1
 */
public class ServerBootLoader {
    public static void main(String... args) {
        try {
            addHandler("/", (request, response) -> {
                System.out.println(request.attribute("a"));
                System.out.println(request.attribute("b"));

                response.type("text/plain");
                response.body("hello world");
            });

            new CartmanServer().start();
        } catch (CertificateException | SSLException e) {
            e.printStackTrace();
        }
    }
}
