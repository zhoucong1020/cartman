package cn.icodeit.cartman.core;

import cn.icodeit.cartman.core.annotation.Param;
import cn.icodeit.cartman.core.annotation.RequestMethod;
import cn.icodeit.cartman.core.annotation.Service;
import cn.icodeit.cartman.core.annotation.ServiceMethod;
import cn.icodeit.cartman.core.utils.ClassScanUtils;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * @author zhoucong
 * @since 0.0.1
 */
public class Cartman extends CartmanBase {

    private Cartman() {
    }

    public synchronized static void scan(String packageName) {
        ClassScanUtils.scan(packageName)
                .forEach(Cartman::scanClass);
    }

    public synchronized static void get(String path, Route route) {
        addRoute(wrap(path, route), RequestMethod.GET);
    }

    public synchronized static void post(String path, Route route) {
        addRoute(wrap(path, route), RequestMethod.POST);
    }

    public synchronized static void put(String path, Route route) {
        addRoute(wrap(path, route), RequestMethod.PUT);
    }

    public synchronized static void delete(String path, Route route) {
        addRoute(wrap(path, route), RequestMethod.DELETE);
    }

    private static void scanClass(Class<?> clazz) {
        Service serviceAnnotation = clazz.getAnnotation(Service.class);
        String prefix = serviceAnnotation == null ?
                clazz.getSimpleName() : serviceAnnotation.value();

        for (Method method : clazz.getDeclaredMethods()) {
            ServiceMethod methodAnnotation = method.getAnnotation(ServiceMethod.class);
            String suffix = methodAnnotation == null ?
                    method.getName() : methodAnnotation.value();
            RequestMethod requestMethod = methodAnnotation == null ?
                    CARTMAN_DEFAULT_METHOD : methodAnnotation.method();
            RouteImpl route = wrap(createPath(prefix, suffix), requestMethod, clazz, method);

            for (Parameter parameter : method.getParameters()) {
                Param paramAnnotation = parameter.getAnnotation(Param.class);
                boolean required = paramAnnotation == null ?
                        CARTMAN_DEFAULT_REQUIRED : paramAnnotation.required();
                String paramName = paramAnnotation == null ?
                        parameter.getName() : paramAnnotation.value();

                ParamImpl param = new ParamImpl(paramName, parameter.getType(), required);
                route.addParam(param);
            }

            addRoute(route, requestMethod);
        }
    }

    private static String createPath(String prefix, String suffix) {
        return (prefix.startsWith("/") ? prefix : "/" + prefix)
                + (suffix.startsWith("/") ? suffix : "/" + suffix);
    }
}
