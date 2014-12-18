package cn.icodeit.cartman.core.service;

/**
 * @author zhoucong
 */
public interface Converter {

    public String serialize(Object object);

    public <T> T deserialize(String paramString, Class<T> convertClazz);
}
