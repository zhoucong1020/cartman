package cn.icodeit.cartman.core.annotation.mode;

import cn.icodeit.cartman.core.annotation.MethodField;
import cn.icodeit.cartman.core.annotation.mode.convert.Convert;
import cn.icodeit.cartman.core.io.Request;

/**
 * .
 * User: yuanweifeng
 * Date: 14-11-19
 * Time: 下午4:13
 */

public interface Interaction {

    String getRequestPrefix(Request request);

    String execute(Request request, Convert convert);

    MethodField getRequestMethod(Request request);

}
