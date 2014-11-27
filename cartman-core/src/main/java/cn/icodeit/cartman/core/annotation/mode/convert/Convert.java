package cn.icodeit.cartman.core.annotation.mode.convert;

/**
 * .
 * User: yuanweifeng
 * Date: 14-11-26
 * Time: 上午9:55
 */
public interface Convert {

    Object convert(String paramString, Class convertClazz);

    String stringConvert(Object object);
}
