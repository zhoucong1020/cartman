package cn.icodeit.cartman.core.boot;

import cn.icodeit.cartman.core.io.Request;
import cn.icodeit.cartman.core.io.Response;
import cn.icodeit.cartman.core.service.ActionHandler;
import cn.icodeit.cartman.core.service.ActionInterceptor;

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
        handler.addAInterceptor(new ActionInterceptor() {
            @Override
            public void before(Request request) {
                System.out.println("enter before ........");
            }

            @Override
            public void after(Response response) {
                System.out.println("enter after ........");
            }
        });


        addHandler("/", handler);
    }
}
