package cn.icodeit.cartman.core;

/**
 * @author zhoucong
 * @since 0.0.1
 */
public interface Transformer {

    public String serialize(Object object);

    public <T> T deserialize(String paramString, Class<T> convertClazz);
}
