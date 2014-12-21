package cn.icodeit.cartman.core;

import cn.icodeit.cartman.core.utils.CartmanUtils;
import com.alibaba.fastjson.JSON;

/**
 * @author zhoucong
 */
public class TransformerJsonImpl implements Transformer {

    private static Transformer instance = new TransformerJsonImpl();

    @Override
    public String serialize(Object object) {
        if (object.getClass().isPrimitive() || CartmanUtils.isWrapClass(object.getClass())) {
            return object.toString();
        } else {
            return JSON.toJSONString(object);
        }
    }

    @Override
    public <T> T deserialize(String paramString, Class<T> convertClazz) {
        return JSON.parseObject(paramString, convertClazz);
    }

    public static Transformer getInstance() {
        return instance;
    }


}
