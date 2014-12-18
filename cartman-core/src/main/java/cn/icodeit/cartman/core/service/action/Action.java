package cn.icodeit.cartman.core.service.action;

import cn.icodeit.cartman.core.annotation.mode.convert.Convert;
import cn.icodeit.cartman.core.annotation.mode.convert.JsonConvert;
import cn.icodeit.cartman.core.io.Request;
import cn.icodeit.cartman.core.service.ActionContext;
import cn.icodeit.cartman.core.service.ActionInterceptor;
import cn.icodeit.cartman.core.service.mapping.ActionMapping;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhoucong
 * @since 0.0.1
 */
public abstract class Action implements Runnable {

    private ActionContext context;
    private List<ActionInterceptor> interceptors;

    public Action(ActionContext context) {
        this.context = context;
    }

    public abstract String getAttribute(String annotationName, Request request, boolean required);

    @Override
    public void run() {
        context.getResponse().type("application/json");

        if(interceptors!=null && !interceptors.isEmpty()){
            interceptors.forEach(e->{
                e.before(context.getRequest());
                Object res = invoke(context.getActionMapping(), getParams(context.getActionMapping(), context.getRequest(), JsonConvert.getInstance()).toArray());
                context.getResponse().body(JsonConvert.getInstance().stringConvert(res));
                e.after(context.getResponse());
            });
        }else {
            Object res = invoke(context.getActionMapping(), getParams(context.getActionMapping(), context.getRequest(), JsonConvert.getInstance()).toArray());
            context.getResponse().body(JsonConvert.getInstance().stringConvert(res));
        }
    }


    private List getParams(ActionMapping actionMapping, Request request, Convert convert) {
        List result = new ArrayList();
        actionMapping.getParams().forEach(e -> {
            String attribute = getAttribute(e.getAnnotationName(), request, e.isRequired());
            attribute = URLDecoder.decode(attribute);
            result.add(convert.convert(attribute, e.getClassType()));
        });

        return result;
    }

    public Object invoke(ActionMapping actionMapping, Object... args) {
        try {
            Class clazz = actionMapping.getClazz();
            Method method = actionMapping.getMethod();
            //TODO 采用对象池实现
            Object invokerObject = clazz.newInstance();

            if (invokerObject == null) {
                invokerObject = clazz.newInstance();
            }

            return method.invoke(invokerObject, args);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }


    public void setInterceptors(List<ActionInterceptor> interceptors) {
        this.interceptors = interceptors;
    }
}
