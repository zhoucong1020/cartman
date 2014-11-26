package cn.icodeit.cartman.core.annotation.mode;

import cn.icodeit.cartman.core.annotation.MethodField;
import cn.icodeit.cartman.core.annotation.mode.convert.Convert;
import cn.icodeit.cartman.core.io.Request;

import java.util.List;

/**
 * .
 * User: yuanweifeng
 * Date: 14-11-19
 * Time: 下午4:13
 */

public interface Interaction {

    public String getRequestPrefix(Request request);

    List<Object> getParams(Request request, Convert convert);

    String execute(Request request);

    MethodField getRequestMethod(Request request);
}
