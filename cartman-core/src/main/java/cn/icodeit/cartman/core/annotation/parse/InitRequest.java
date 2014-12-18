package cn.icodeit.cartman.core.annotation.parse;

import cn.icodeit.cartman.core.annotation.Mapping;
import cn.icodeit.cartman.core.annotation.Service;
import cn.icodeit.cartman.core.annotation.mode.AccessElement;
import cn.icodeit.cartman.core.annotation.mode.ParamElement;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.Enumeration;

import static cn.icodeit.cartman.core.annotation.parse.InitServiceCall.createKey;
import static cn.icodeit.cartman.core.annotation.parse.InitServiceCall.put;

/**
 * .
 * User: yuanweifeng
 * Date: 14-12-1
 * Time: 上午10:53
 */
public class InitRequest {

    public static void scanner(String packageName) {
        String packagePath = packageName.replace(".", "/");

        try {
            Enumeration<URL> dirs = Thread.currentThread().getContextClassLoader().getResources(packagePath);
            while (dirs.hasMoreElements()) {
                URL url = dirs.nextElement();
                if (url.getProtocol().equals("file")) {
                    String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
                    scanPath(filePath, packageName);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void scanPath(String filePath, String packageName) {
        File path = new File(filePath);
        if (!path.exists()) {
            return;
        }

        File[] files = path.listFiles(e -> e.isDirectory() || e.getName().endsWith(".class"));
        for (File file : files) {
            if (file.isDirectory()) {
                scanPath(filePath + "/" + file.getName(), packageName + "." + file.getName());
            } else {
                Class<?> clazz = getClass(packageName + "." + file.getName().substring(0, file.getName().length() - 6));
                if (clazz != null) {
                    mapClass(clazz);
                }
            }
        }
    }

    private static Class<?> getClass(String className) {
        try {
            return Thread.currentThread().getContextClassLoader().loadClass(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static void mapClass(Class<?> clazz) {
        Service service = clazz.getAnnotation(Service.class);

        String serviceName = service == null ? clazz.getSimpleName() : service.value();

        for (Method method : clazz.getDeclaredMethods()) {
            Mapping mapping = method.getAnnotation(Mapping.class);

            AccessElement element = new AccessElement(clazz, method);
            if (service != null) {
                element.setRequestMethod(service.method());
            }

            String mappingName;
            if (mapping == null) {
                mappingName = method.getName();
            } else {
                mappingName = mapping.value();
                element.setRequestMethod(mapping.method());
            }

            for (Parameter parameter : method.getParameters()) {
                cn.icodeit.cartman.core.annotation.Param param =
                        parameter.getAnnotation(cn.icodeit.cartman.core.annotation.Param.class);

                String paramName;
                boolean required = true;
                if (param == null) {
                    paramName = parameter.getName();
                } else {
                    paramName = param.value();
                    required = param.required();
                }
                ParamElement paramElement = new ParamElement(paramName, parameter.getType(), required);
                element.addParam(paramElement);
            }

            String key = createKey(serviceName, mappingName);
            put(key, element);
        }
    }
}
