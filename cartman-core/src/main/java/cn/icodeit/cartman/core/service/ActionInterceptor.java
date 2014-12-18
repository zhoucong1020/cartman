package cn.icodeit.cartman.core.service;

import cn.icodeit.cartman.core.io.Request;
import cn.icodeit.cartman.core.io.Response;

/**
 * .
 * User: yuanweifeng
 * Date: 14-12-17
 * Time: 上午9:52
 */
public interface ActionInterceptor {

    void before(Request request);

    void after(Response response);
}
