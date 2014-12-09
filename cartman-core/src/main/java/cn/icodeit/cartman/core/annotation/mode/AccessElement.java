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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AccessElement element = (AccessElement) o;

        if (clazz != null ? !clazz.equals(element.clazz) : element.clazz != null) return false;
        if (method != null ? !method.equals(element.method) : element.method != null) return false;
        if (params != null ? !params.equals(element.params) : element.params != null) return false;
        if (requestMethod != element.requestMethod) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = clazz != null ? clazz.hashCode() : 0;
        result = 31 * result + (method != null ? method.hashCode() : 0);
        result = 31 * result + (requestMethod != null ? requestMethod.hashCode() : 0);
        result = 31 * result + (params != null ? params.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AccessElement{" +
                "clazz=" + clazz.getSimpleName() +
                ", method=" + method.getName() +
                ", params=" + params +
                '}';
    }
}