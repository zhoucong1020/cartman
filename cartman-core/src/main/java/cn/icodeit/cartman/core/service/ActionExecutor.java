package cn.icodeit.cartman.core.service;

import cn.icodeit.cartman.core.service.action.Action;
import cn.icodeit.cartman.core.service.action.ActionFactory;

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

        aAction(context).run();
    }

    public void submit(ActionContext context, ActionInterceptors actionInterceptors) {
        Action action = aAction(context);
        action.setInterceptors(actionInterceptors.getInterceptors());
        action.run();

    }

    public Action aAction(ActionContext context){
        Action action  = null;
        //TODO netty引用计数问题导致暂时无法异步执行
        //executor.submit(ActionFactory.createAction(context));
        action = ActionFactory.createAction(context);
        return action;
    }
}
