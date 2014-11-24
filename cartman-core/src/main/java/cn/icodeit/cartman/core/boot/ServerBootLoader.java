package cn.icodeit.cartman.core.boot;

import cn.icodeit.cartman.io.Server;
import cn.icodeit.cartman.io.context.ServerContext;
import cn.icodeit.cartman.io.server.CartmanServer;
import cn.icodeit.cartman.io.server.netty.NettyServer;

import javax.net.ssl.SSLException;
import java.security.cert.CertificateException;

/**
 * @author zhoucong
 * @since 0.0.1
 */
public class ServerBootLoader {
    public static void main(String... args) {
        run(NettyServer.class, args);
    }

    public static void run(Class<?> clazz, String... args) {
        try {
            CartmanServer.addHandler("/", (request, response) -> {
                System.out.println(request.attribute("a"));
                System.out.println(request.attribute("b"));

                response.type("text/plain");
                response.body("hello world");
            });

            Server server = (Server) clazz.newInstance();
            server.start(new ServerContext(false, 8080));
        } catch (InstantiationException | IllegalAccessException | CertificateException | SSLException e) {
            e.printStackTrace();
        }
    }
}
