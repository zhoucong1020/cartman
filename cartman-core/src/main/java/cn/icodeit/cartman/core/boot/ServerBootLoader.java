package cn.icodeit.cartman.core.boot;

import cn.icodeit.cartman.core.service.ActionHandler;

import static cn.icodeit.cartman.core.io.Cartman.addHandler;
import static cn.icodeit.cartman.core.service.ActionConfiguration.scan;

/**
 * @author zhoucong
 * @since 0.0.1
 */
public class ServerBootLoader {

    public static void main(String... args) {
        scan("cn.icodeit.cartman.core.boot.testService");

        ActionHandler handler = new ActionHandler();
        handler.init();

        addHandler("/", handler);
    }
}
