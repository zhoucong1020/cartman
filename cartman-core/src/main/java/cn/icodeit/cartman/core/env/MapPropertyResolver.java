package cn.icodeit.cartman.core.env;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhoucong
 */
public class MapPropertyResolver implements PropertyResolver {

    protected Map<String, Object> map = new HashMap<>();

    @Override
    public boolean contains(String key) {
        return map.containsKey(key);
    }

    @Override
    public String get(String key) {
        return get(key, String.class);
    }

    @Override
    public String get(String key, String def) {
        return get(key, String.class, def);
    }

    @Override
    public <T> T get(String key, Class<T> clazz) {
        return (T) map.get(key);
    }

    @Override
    public <T> T get(String key, Class<T> clazz, T def) {
        T res = (T) map.get(key);
        return res == null ? def : res;
    }
}
