package cn.icodeit.cartman.core;

import cn.icodeit.cartman.core.Action;
import cn.icodeit.cartman.core.server.Request;

import java.lang.reflect.Method;

/**
 * @author zhoucong
 * @since 0.0.1
 */
public class ActionPutImpl extends Action {

    public ActionPutImpl(String path, Class<?> clazz, Method method) {
        super(path, clazz, method);
    }

    @Override
    public String getAttribute(String annotationName, Request request, boolean required) {
        String attribute = request.attribute(annotationName);
        if (required && attribute == null) {
            throw new IllegalArgumentException
                    ("request parameters less than expected " + annotationName);
        }
        return attribute;
    }
}
