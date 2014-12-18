package cn.icodeit.cartman.core.annotation.mode.Interaction.requestMethod;

import cn.icodeit.cartman.core.annotation.MethodField;

import java.util.HashMap;
import java.util.Map;

/**
 * .
 * User: yuanweifeng
 * Date: 14-12-8
 * Time: 上午10:51
 */
public class RequestCreator {
    private static Map<MethodField, RequestMethod> map = new HashMap<>();

    static {
        map.put(MethodField.GET, new GetType());
        map.put(MethodField.POST, new PostType());
    }

    public static RequestMethod create(MethodField field) {
        RequestMethod result = map.get(field);
        if (result == null)
            throw new IllegalArgumentException("un handler request method " + field);
        return result;
    }

}
