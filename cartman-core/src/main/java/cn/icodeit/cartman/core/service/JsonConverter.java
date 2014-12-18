package cn.icodeit.cartman.core.service;

import com.alibaba.fastjson.JSON;

/**
 * @author zhoucong
 */
public class JsonConverter implements Converter {

    private static Converter instance = new JsonConverter();

    @Override
    public String serialize(Object object) {
        return JSON.toJSONString(object);
    }

    @Override
    public <T> T deserialize(String paramString, Class<T> convertClazz) {
        return JSON.parseObject(paramString, convertClazz);
    }

    public static Converter getInstance() {
        return instance;
    }
}
