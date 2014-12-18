package cn.icodeit.cartman.doc.provider;

import cn.icodeit.cartman.core.io.AbstractHandler;
import cn.icodeit.cartman.core.io.Handler;
import cn.icodeit.cartman.core.io.Request;
import cn.icodeit.cartman.core.io.Response;
import cn.icodeit.cartman.core.service.Converter;
import cn.icodeit.cartman.core.service.JsonConverter;

import java.util.Date;

/**
 * Created by lcf on 2014/12/1.
 */
public class DocHandler<T> extends AbstractHandler {
    T t;

    public DocHandler(T t) {
        this.t = t;
    }

    @Override
    public void handle(Request request, Response response) {
        response.type("application/json");
        Converter instance = JsonConverter.getInstance();
        System.out.println(new Date() +"\t"+ request.uri());
        response.body(instance.serialize(t));
    }
}
