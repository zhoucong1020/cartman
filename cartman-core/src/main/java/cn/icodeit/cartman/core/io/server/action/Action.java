package cn.icodeit.cartman.core.io.server.action;

import cn.icodeit.cartman.core.io.Request;
import cn.icodeit.cartman.core.io.server.ActionContext;
import cn.icodeit.cartman.core.service.convert.Converter;
import cn.icodeit.cartman.core.service.convert.JsonConverter;
import cn.icodeit.cartman.core.service.mapping.ActionMapping;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
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
        try {
            context.response().type("application/json");
            Object res = invoke(context.mapping(), getParams(context.mapping(), context.request(), JsonConverter.getInstance()).toArray());
            context.response().body(JsonConverter.getInstance().stringConvert(res));
        } catch (Exception e) {
            context.response().status(500);
            context.response().body(getStackTrace(e));
        } finally {
            context.flush();
        }
    }

    private List getParams(ActionMapping actionMapping, Request request, Converter converter) {
        List result = new ArrayList();
        actionMapping.getParams().forEach(e -> {
            String attribute = getAttribute(e.getAnnotationName(), request, e.isRequired());
            attribute = URLDecoder.decode(attribute);
            result.add(converter.convert(attribute, e.getClassType()));
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

    private String getStackTrace(Throwable throwable) {
        final Writer result = new StringWriter();
        final PrintWriter printWriter = new PrintWriter(result);
        throwable.printStackTrace(printWriter);
        return result.toString();
    }
}
