package cn.icodeit.cartman.doc.provider;

import cn.icodeit.cartman.core.annotation.mode.Interaction.AbstractInteraction;
import cn.icodeit.cartman.core.annotation.mode.Interaction.Interaction;
import cn.icodeit.cartman.core.annotation.mode.convert.Convert;
import cn.icodeit.cartman.core.annotation.mode.convert.JsonConvert;
import cn.icodeit.cartman.core.io.Handler;
import cn.icodeit.cartman.core.io.Request;
import cn.icodeit.cartman.core.io.Response;

import java.util.Date;

import static cn.icodeit.cartman.core.annotation.parse.InitServiceCall.addExcludeKey;

/**
 * Created by lcf on 2014/12/1.
 */
public class DocHandler<T> extends AbstractInteraction implements Handler, Interaction {
    T t;

    public DocHandler(T t) {
        this.t = t;
    }

    @Override
    public void handle(Request request, Response response) {
        response.type("application/json");
        Convert instance = JsonConvert.getInstance();
        System.out.println(new Date() +"\t"+ request.uri());
        response.body(instance.stringConvert(t));
    }
}
