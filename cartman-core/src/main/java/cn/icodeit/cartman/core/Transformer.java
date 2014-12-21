package cn.icodeit.cartman.core;

/**
 * @author zhoucong
 */
public interface Transformer {

    public String serialize(Object object);

    public <T> T deserialize(String paramString, Class<T> convertClazz);
}
