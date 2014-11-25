package cn.icodeit.cartman.core.boot;

import cn.icodeit.cartman.core.io.server.CartmanServer;

import javax.net.ssl.SSLException;
import java.security.cert.CertificateException;

import static cn.icodeit.cartman.core.io.Cartman.addHandler;

/**
 * @author zhoucong
 * @since 0.0.1
 */
public class ServerBootLoader {
    public static void main(String... args) {
        addHandler("/", (request, response) -> {
            response.type("application/json");
            response.body("{\"响应测试\":\"中文\",\"a\":\"\"}");
        });
    }
}
