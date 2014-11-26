package cn.icodeit.cartman.core.annotation.parse;

import cn.icodeit.cartman.core.annotation.mode.AbstractInteraction;
import cn.icodeit.cartman.core.annotation.mode.AccessElement;
import cn.icodeit.cartman.core.annotation.mode.convert.Convert;
import cn.icodeit.cartman.core.io.Handler;
import cn.icodeit.cartman.core.io.Request;
import cn.icodeit.cartman.core.io.Response;

import java.util.ArrayList;
import java.util.List;

import static cn.icodeit.cartman.core.annotation.parse.InitServiceCall.get;

/**
 * .
 * User: yuanweifeng
 * Date: 14-11-19
 * Time: 下午5:06
 */
public class MethodInvoker extends AbstractInteraction implements Handler {

    @Override
    public void handle(Request request, Response response) {

        response.type("application/json");
        response.body(execute(request));

    }




}
