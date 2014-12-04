package cn.icodeit.cartman.core.annotation.parse;

import cn.icodeit.cartman.core.annotation.mode.Interaction.Interaction.AbstractInteraction;
import cn.icodeit.cartman.core.annotation.mode.Interaction.Interaction;
import cn.icodeit.cartman.core.annotation.mode.convert.Convert;
import cn.icodeit.cartman.core.annotation.mode.convert.JsonConvert;
import cn.icodeit.cartman.core.io.Handler;
import cn.icodeit.cartman.core.io.Request;
import cn.icodeit.cartman.core.io.Response;

import static cn.icodeit.cartman.core.annotation.parse.InitServiceCall.addExcludeKey;

/**
 * .
 * User: yuanweifeng
 * Date: 14-11-19
 * Time: 下午5:06
 */
public class MethodInvoker extends AbstractInteraction implements Handler, Interaction {

    public void handle(Request request, Response response) {
        addExcludeKey("favicon.ico");

        response.type("application/json");
        Convert instance = JsonConvert.getInstance();
        response.body(execute(request, instance));

    }


}
