package cn.icodeit.cartman.core;

import cn.icodeit.cartman.core.server.Request;

import java.lang.reflect.Method;

/**
 * @author zhoucong
 */
public class ActionGetImpl extends Action {

    public ActionGetImpl(String path, Class<?> clazz, Method method) {
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
