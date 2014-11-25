package cn.icodeit.cartman.core.annotation.mode;

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
    private List<ParamElement> params = new ArrayList<>();

    public AccessElement(Class clazz, Method method) {
        this.clazz = clazz;
        this.method = method;
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