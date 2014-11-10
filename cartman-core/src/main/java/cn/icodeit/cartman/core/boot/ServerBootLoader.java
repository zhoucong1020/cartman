package cn.icodeit.cartman.core.boot;

import cn.icodeit.cartman.io.Server;

/**
 * @author zhoucong
 * @since 0.0.1
 */
public class ServerBootLoader {
    public static void run(Class<Server> clazz, String... args) {
        try {
            Server server = clazz.newInstance();
            server.start(null);
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
