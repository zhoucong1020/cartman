package cn.icodeit.cartman.core.service.action;

import cn.icodeit.cartman.core.io.Request;
import cn.icodeit.cartman.core.service.ActionContext;
import cn.icodeit.cartman.core.service.Converter;
import cn.icodeit.cartman.core.service.JsonConverter;
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

    public Action(ActionContext context) {
        this.context = context;
    }

    public abstract String getAttribute(String annotationName, Request request, boolean required);

    @Override
    public void run() {
        context.getResponse().type("application/json");
        Object res = invoke(context.getActionMapping(), getParams(context.getActionMapping(), context.getRequest(), JsonConverter.getInstance()).toArray());
        context.getResponse().body(JsonConverter.getInstance().serialize(res));
    }

    private List getParams(ActionMapping actionMapping, Request request, Converter convert) {
        List result = new ArrayList();
        actionMapping.getParams().forEach(e -> {
            String attribute = getAttribute(e.getAnnotationName(), request, e.isRequired());
            attribute = URLDecoder.decode(attribute);
            result.add(convert.deserialize(attribute, e.getClassType()));
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
}
