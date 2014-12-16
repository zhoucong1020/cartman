package cn.icodeit.cartman.core.service.mapping;

import cn.icodeit.cartman.core.service.annotation.RequestMethod;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhoucong
 */
public class ActionMapping {
    private Class clazz;
    private Method method;
    private RequestMethod requestMethod = RequestMethod.POST;
    private List<ParamMapping> params = new ArrayList<>();

    public ActionMapping(Class clazz, Method method) {
        this.clazz = clazz;
        this.method = method;

    }

    public RequestMethod getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(RequestMethod requestMethod) {
        this.requestMethod = requestMethod;
    }

    public Class getClazz() {
        return clazz;
    }


    public Method getMethod() {
        return method;
    }


    public List<ParamMapping> getParams() {
        return params;
    }

    public void addParam(ParamMapping paramElement) {
        params.add(paramElement);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ActionMapping mapping = (ActionMapping) o;

        if (clazz != null ? !clazz.equals(mapping.clazz) : mapping.clazz != null) return false;
        if (method != null ? !method.equals(mapping.method) : mapping.method != null) return false;
        if (params != null ? !params.equals(mapping.params) : mapping.params != null) return false;
        if (requestMethod != mapping.requestMethod) return false;

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
