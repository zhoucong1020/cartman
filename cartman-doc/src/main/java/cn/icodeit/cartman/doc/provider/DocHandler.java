package cn.icodeit.cartman.doc.provider;

import cn.icodeit.cartman.core.Route;
import cn.icodeit.cartman.core.Transformer;
import cn.icodeit.cartman.core.TransformerJsonImpl;
import cn.icodeit.cartman.core.server.Request;
import cn.icodeit.cartman.core.server.Response;

import java.util.Date;

/**
 * Created by lcf on 2014/12/1.
 */
public class DocHandler<T> implements Route {
    T t;

    public DocHandler(T t) {
        this.t = t;
    }

    @Override
    public void handle(Request request, Response response) {
        response.type("application/json");
        Transformer instance = TransformerJsonImpl.getInstance();
        System.out.println(new Date() + "\t" + request.url());
        response.body(instance.serialize(t));
    }
}
