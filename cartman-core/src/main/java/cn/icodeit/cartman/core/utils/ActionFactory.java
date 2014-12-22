package cn.icodeit.cartman.core.utils;


import cn.icodeit.cartman.core.Action;
import cn.icodeit.cartman.core.ActionGetImpl;
import cn.icodeit.cartman.core.ActionPostImpl;
import cn.icodeit.cartman.core.ServiceException;
import cn.icodeit.cartman.core.annotation.RequestMethod;

import java.lang.reflect.Method;

/**
 * @author zhoucong
 * @since 0.0.1
 */
public class ActionFactory {
    public static Action createAction(String path, RequestMethod requestMethod, Class<?> clazz, Method method) {
        switch (requestMethod) {
            //TODO: ->ywf "添加put delete类型"
            case GET:
                return new ActionGetImpl(path, clazz, method);
            case POST:
                return new ActionPostImpl(path, clazz, method);
            default:
                throw new ServiceException(405, "method not allowed for '" + path + "'");
        }
    }
}
