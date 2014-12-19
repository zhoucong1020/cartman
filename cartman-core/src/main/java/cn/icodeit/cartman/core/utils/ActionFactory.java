package cn.icodeit.cartman.core.utils;


import cn.icodeit.cartman.core.Action;
import cn.icodeit.cartman.core.GetAction;
import cn.icodeit.cartman.core.PostAction;
import cn.icodeit.cartman.core.ServiceException;
import cn.icodeit.cartman.core.annotation.RequestMethod;

import java.lang.reflect.Method;

/**
 * @author zhoucong
 */
public class ActionFactory {
    public static Action createAction(String path, RequestMethod requestMethod, Class<?> clazz, Method method) {
        switch (requestMethod) {
            case GET:
                return new GetAction(path, clazz, method);
            case POST:
                return new PostAction(path, clazz, method);
            default:
                throw new ServiceException(405, "method not allowed for '" + path + "'");
        }
    }
}
