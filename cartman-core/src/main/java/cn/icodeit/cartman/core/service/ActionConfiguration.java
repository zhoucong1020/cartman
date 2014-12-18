package cn.icodeit.cartman.core.service;

import cn.icodeit.cartman.core.service.annotation.RequestMethod;
import cn.icodeit.cartman.core.service.mapping.ActionMapping;

/**
 * @author zhoucong
 * @since 0.0.1
 */
public class ActionConfiguration {

    private static ClassMapper classMapper = new ClassMapper();

    public static void scan(String packageName) {
        ClassScanner.scan(packageName).forEach(classMapper::mapClass);
    }

    public static ActionMapping getMapping(String uri, RequestMethod requestMethod) {
        return classMapper.get(uri, requestMethod);
    }
}
