package cn.icodeit.cartman.core.annotation.mode;

import java.util.List;

/**
 * .
 * User: yuanweifeng
 * Date: 14-11-19
 * Time: 下午4:13
 */

public interface Interaction {

    List<Object> getParamObject();

    public default Object execute(AccessElement element) {
        return Location.invoker(element.getClazz(), element.getMethod(), getParamObject());
    }
}
