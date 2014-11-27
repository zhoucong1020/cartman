package cn.icodeit.cartman.core.annotation.mode;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * .
 * User: yuanweifeng
 * Date: 14-11-19
 * Time: 下午4:30
 */
class Location {
    private static Map<Class, Object> map = new HashMap<>();

    public static Object invoker(AccessElement element, Object... args) {
        try {
            Class clazz = element.getClazz();
            Method method = element.getMethod();
            Object invokerObject = map.get(clazz);

            if (invokerObject == null) {
                invokerObject = clazz.newInstance();
                map.put(clazz, invokerObject);
            }

            return method.invoke(invokerObject, args);

        } catch (InstantiationException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IllegalAccessException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InvocationTargetException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return null;

    }

}
