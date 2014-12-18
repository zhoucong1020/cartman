package cn.icodeit.cartman.core.utils;

import cn.icodeit.cartman.core.io.exception.ServiceException;
import cn.icodeit.cartman.core.service.annotation.Param;
import cn.icodeit.cartman.core.service.annotation.RequestMethod;
import cn.icodeit.cartman.core.service.annotation.Service;
import cn.icodeit.cartman.core.service.annotation.ServiceMethod;
import cn.icodeit.cartman.core.service.mapping.ActionMapping;
import cn.icodeit.cartman.core.service.mapping.ParamMapping;

import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhoucong
 */
public class ClassMapper {

    private Map<String, List<ActionMapping>> requestMapping = new HashMap<>();

    public void mapClass(Class<?> clazz) {
        Service service = clazz.getAnnotation(Service.class);

        String serviceName = service == null ? clazz.getSimpleName() : service.value();

        for (java.lang.reflect.Method method : clazz.getDeclaredMethods()) {
            ServiceMethod serviceMethod = method.getAnnotation(ServiceMethod.class);

            ActionMapping actionMapping = new ActionMapping(clazz, method);
            if (service != null) {
                actionMapping.setRequestMethod(service.method());
            }

            String mappingName;
            if (serviceMethod == null) {
                mappingName = method.getName();
            } else {
                mappingName = serviceMethod.value();
                actionMapping.setRequestMethod(serviceMethod.method());
            }

            for (Parameter parameter : method.getParameters()) {
                Param param = parameter.getAnnotation(Param.class);

                String paramName;
                boolean required = true;
                if (param == null) {
                    paramName = parameter.getName();
                } else {
                    paramName = param.value();
                    required = param.required();
                }
                ParamMapping paramMapping = new ParamMapping(paramName, parameter.getType(), required);
                actionMapping.addParam(paramMapping);
            }

            String key = createKey(serviceName, mappingName);
            put(key, actionMapping);
        }
    }

    public ActionMapping get(String uri, RequestMethod method) throws IllegalAccessError, IllegalArgumentException {
        isUndefined(uri);

        List<ActionMapping> elements = requestMapping.get(uri);
        for (ActionMapping element : elements) {
            if (element.getRequestMethod().equals(method)) {
                return element;
            }
        }
        throw new ServiceException(405, "request for '" + uri + "' method '" + method + "' not allowed.");
    }

    private void put(String key, ActionMapping value) {
        List<ActionMapping> elements = requestMapping.get(key);
        if (elements == null) {
            elements = new ArrayList<>(4);
            elements.add(value);
            requestMapping.put(key, elements);
            return;
        }
        if (!elements.contains(value)) {
            elements.forEach(e -> {
                if (e.getRequestMethod().equals(value.getRequestMethod()))
                    throw new IllegalArgumentException
                            ("init request :" + key + " method: " + value + " error!" +
                                    value.getRequestMethod() + " has already defined!");
            });
            elements.add(value);
        }
    }

    private String createKey(String serviceName, String mappingName) {
        String fullKey = serviceName + "/" + mappingName;
        return fullKey.trim();
    }

    private void isUndefined(String key) {
        if (!isContainKey(key)) {
            throw new ServiceException(404, "undefined request '" + key + "'");
        }
    }

    private boolean isContainKey(String key) {
        return requestMapping.get(key) != null;
    }
}
