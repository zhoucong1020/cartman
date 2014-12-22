package cn.icodeit.cartman.core.utils;

/**
 * @author zhoucong
 * @since 0.0.1
 */
public class BeanFactory {

    public static Object get(Class<?> clazz) throws IllegalAccessException, InstantiationException {
        return clazz.newInstance();
    }
}
