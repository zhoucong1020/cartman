package cn.icodeit.cartman.core;

import cn.icodeit.cartman.core.server.Request;

import java.lang.reflect.Method;

/**
 * @author zhoucong
 */
public class ActionPostImpl extends Action {

    public ActionPostImpl(String path, Class<?> clazz, Method method) {
        super(path, clazz, method);
    }

    @Override
    public String getAttribute(String annotationName, Request request, boolean required) {
        String body = request.body();

        int i = body.indexOf(annotationName + "=");
        String substring = body.substring(i);
        int i1 = substring.indexOf("&");
        if (i1 == -1) {
            return substring.split("=")[1];
        }
        String substring1 = body.substring(i, i1 + i);
        String attribute = substring1.split("=")[1];
        if (required && attribute == null) {
            throw new IllegalArgumentException
                    ("request parameters less than expected " + annotationName);
        }
        return attribute;
    }
}
