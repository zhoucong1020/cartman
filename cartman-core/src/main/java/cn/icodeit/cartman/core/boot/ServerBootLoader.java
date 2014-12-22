package cn.icodeit.cartman.core.boot;

import cn.icodeit.cartman.core.Cartman;

/**
 * @author zhoucong
 * @since 0.0.1
 */
public class ServerBootLoader {

    public static void main(String... args) {
        Cartman.port(8080);
        Cartman.scan("cn.icodeit.cartman.core.boot.testService");
        Cartman.get("/doc.json", (req, res) -> {
            res.body("{}");
        });
    }
}
