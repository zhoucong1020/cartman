package cn.icodeit.cartman.core.boot;

import cn.icodeit.cartman.core.annotation.parse.MethodInvoker;


import static cn.icodeit.cartman.core.annotation.parse.InitRequest.scanner;
import static cn.icodeit.cartman.core.io.Cartman.addHandler;

/**
 * @author zhoucong
 * @since 0.0.1
 */
public class ServerBootLoader {
    public static void main(String... args) {
       /* addHandler("/", (request, response) -> {
            response.type("text/html");
            response.body("{\"响应测试\":\"中文\",\"a\":\"\"}");
        });*/

        scanner("cn.icodeit.cartman.core.boot.testService");

        addHandler("/", new MethodInvoker());
    }
}
