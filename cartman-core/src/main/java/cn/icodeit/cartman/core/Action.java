package cn.icodeit.cartman.core;

import cn.icodeit.cartman.core.server.Request;
import cn.icodeit.cartman.core.server.Response;
import cn.icodeit.cartman.core.utils.BeanFactory;
import cn.icodeit.cartman.core.utils.CartmanUtils;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhoucong
 * @since 0.0.1
 */
public abstract class Action {

    private String path;
    private Class<?> clazz;
    private Method method;
    private List<ParamImpl> params = new ArrayList<>();

    public Action(String path, Class<?> clazz, Method method) {
        this.path = path;
        this.clazz = clazz;
        this.method = method;
    }

    public abstract String getAttribute(String annotationName, Request request, boolean required);

    public String getPath() {
        return path;
    }

    public void handle(Request request, Response response) {
        //TODO: type 应该根据transformer类型确定
        response.type("application/json");
        try {
            Object result = method.invoke(
                    BeanFactory.get(clazz),
                    getParams(request, TransformerJsonImpl.getInstance()).toArray());
            response.status(200);
            if (result == null) {
                response.body("");
            } else {
                response.body(CartmanBase.converter().serialize(result));
            }
        } catch (ServiceException e) {
            response.status(e.getCode());
            response.body(CartmanUtils.getExceptionTrack(e));
        } catch (Exception e) {
            response.status(500);
            response.body(CartmanUtils.getExceptionTrack(e));
        }
    }

    public void addParam(ParamImpl param) {
        params.add(param);
    }

    private List getParams(Request request, Transformer transformer) {
        List result = new ArrayList();
        params.forEach(param -> {
            String attribute = getAttribute(param.getName(), request, param.isRequired());
            try {
                attribute = URLDecoder.decode(attribute, "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            result.add(transformer.deserialize(attribute, param.getClazz()));
        });

        return result;
    }
}
