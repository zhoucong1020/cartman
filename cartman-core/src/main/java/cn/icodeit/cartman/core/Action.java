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
        //TODO: ->csj "type 应该根据transformer类型确定，但注意对现有结构影响最小"
        Transformer transformer = Cartman.transformer;
        response.type(transformer.contentType());
        try {
            Object result = method.invoke(
                    BeanFactory.get(clazz),
                    getParams(request, transformer).toArray());
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
                //TODO: ->csj 编码类型应该可配置，应该在Cartman中添加相应方法
                //attribute = URLDecoder.decode(attribute, "utf-8");
                attribute = URLDecoder.decode(attribute, Cartman.CODE_TYPE);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            result.add(transformer.deserialize(attribute, param.getClazz()));
        });

        return result;
    }
}
