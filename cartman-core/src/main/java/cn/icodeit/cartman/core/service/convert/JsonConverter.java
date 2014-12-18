package cn.icodeit.cartman.core.service.convert;

import com.alibaba.fastjson.JSON;

/**
 * @author zhoucong
 */
public class JsonConverter implements Converter {

    private static Converter jsonInstance = new JsonConverter();

    private JsonConverter() {
    }

    @Override
    public Object convert(String paramString, Class convertClazz) {
        return JSON.parseObject(paramString, convertClazz);
    }

    @Override
    public String stringConvert(Object object) {
        return JSON.toJSONString(object);
    }

    public static Converter getInstance() {
        return jsonInstance;
    }
}
