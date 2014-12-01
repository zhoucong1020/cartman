package cn.icodeit.cartman.core.annotation.mode;

import cn.icodeit.cartman.core.annotation.MethodField;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * .
 * User: yuanweifeng
 * Date: 14-11-24
 * Time: 上午9:27
 */
public class AccessElement {
    private Class clazz;
    private Method method;
    private MethodField requestMethod = MethodField.POST;
    private List<ParamElement> params = new ArrayList<>();

    public AccessElement(Class clazz, Method method) {
        this.clazz = clazz;
        this.method = method;

    }

    public MethodField getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(MethodField requestMethod) {
        this.requestMethod = requestMethod;
    }

    public Class getClazz() {
        return clazz;
    }


    public Method getMethod() {
        return method;
    }


    public List<ParamElement> getParams() {
        return params;
    }

    public void addParam(ParamElement paramElement) {
        params.add(paramElement);
    }

}