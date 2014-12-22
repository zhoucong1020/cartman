package cn.icodeit.cartman.core;

import cn.icodeit.cartman.core.utils.CartmanUtils;
import com.alibaba.fastjson.JSON;

/**
 * @author zhoucong
 * @since 0.0.1
 */
public class TransformerJsonImpl implements Transformer {

    private static Transformer instance = new TransformerJsonImpl();

    public static Transformer getInstance() {
        return instance;
    }

    @Override
    public String serialize(Object object) {
        if (object.getClass().isPrimitive() || CartmanUtils.isWrapClass(object.getClass())) {
            return object.toString();
        } else {
            return JSON.toJSONString(object);
        }
    }

    @Override
    public <T> T deserialize(String str, Class<T> convertClazz) {
        return JSON.parseObject(str, convertClazz);
    }
}
