package cn.icodeit.cartman.core.annotation.mode.convert;

import com.alibaba.fastjson.JSON;

/**
 * .
 * User: yuanweifeng
 * Date: 14-11-26
 * Time: 上午9:58
 */
public class JsonConvert implements Convert {

    private static Convert jsonInstance = new JsonConvert();

    private JsonConvert() {
    }


    public Object convert(String paramString, Class convertClazz) {
        return JSON.parseObject(paramString, convertClazz);
    }


    public String stringConvert(Object object) {
        return JSON.toJSONString(object);
    }

    public static Convert getInstance() {
        return jsonInstance;
    }
}
