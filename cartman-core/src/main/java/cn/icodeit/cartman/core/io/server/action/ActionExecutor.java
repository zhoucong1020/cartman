package cn.icodeit.cartman.core.io.server.action;

import cn.icodeit.cartman.core.io.server.ActionContext;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zhoucong
 * @since 0.0.1
 */
public class ActionExecutor {

    private ExecutorService executor;

    public void init() {
        executor = Executors.newSingleThreadExecutor();
    }

    public void stop() {
        executor.shutdown();
    }

    public void submit(ActionContext context) {
        executor.submit(ActionFactory.createAction(context));
    }
}
