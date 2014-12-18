package cn.icodeit.cartman.core.service.convert;

/**
 * @author zhoucong
 */
public interface Converter {

    Object convert(String paramString, Class convertClazz);

    String stringConvert(Object object);
}
