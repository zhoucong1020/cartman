package cn.icodeit.cartman.core.env;

/**
 * @author zhoucong
 * @since 0.0.1
 */
public interface PropertyResolver {
    boolean contains(String key);
    String get(String key);
    String get(String key, String def);
    <T> T get(String key, Class<T> clazz);
    <T> T get(String key, Class<T> clazz, T def);
}
