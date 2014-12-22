package cn.icodeit.cartman.core.utils;

/**
 * @author zhoucong
 */
public class BeanFactory {

    public static Object get(Class<?> clazz) throws IllegalAccessException, InstantiationException {
        return clazz.newInstance();
    }
}
