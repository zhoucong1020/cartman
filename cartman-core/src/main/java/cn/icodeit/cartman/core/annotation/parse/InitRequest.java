package cn.icodeit.cartman.core.annotation.parse;

import cn.icodeit.cartman.core.annotation.Mapping;
import cn.icodeit.cartman.core.annotation.Service;
import cn.icodeit.cartman.core.annotation.mode.AccessElement;
import cn.icodeit.cartman.core.annotation.mode.Interaction.AbstractInteraction;
import cn.icodeit.cartman.core.annotation.mode.ParamElement;

import java.io.File;
import java.util.Arrays;

import static cn.icodeit.cartman.core.annotation.parse.InitServiceCall.*;

/**
 * .
 * User: yuanweifeng
 * Date: 14-12-1
 * Time: 上午10:53
 */
public class InitRequest {

    private static final String PROJECT_PATH = AbstractInteraction.class
            .getClassLoader()
            .getResource("")
            .getPath();

    private static String packageName = "";


    public static void scanner(String annotationPackage) {

        packageName = annotationPackage;
        File file = new File(PROJECT_PATH + packageName.replace(".", "/"));
        if (!file.isDirectory()) {
            throw new IllegalArgumentException
                    ("service package name error, it is a file not a folder!");

        }
        scanService(file);
    }

    private static void scanService(File file) {
        Arrays.asList(file.list()).forEach(e -> {
            Class aClass = null;
            try {
                aClass = Class.forName(packageName + "." + e.replace(".class", ""));
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }

            Service service = (Service) aClass.getAnnotation(Service.class);

            String serviceName = null;
            if (service == null) {
                serviceName = aClass.getSimpleName();
            } else {
                serviceName = service.value();

            }
            Class classEl = aClass;
            String serviceNameCall = serviceName;
            Arrays.asList(aClass.getDeclaredMethods()).forEach(m ->
                    {
                        Mapping mapping = m.getAnnotation(Mapping.class);

                        AccessElement element = new AccessElement(classEl, m);

                        if (service != null) {
                            element.setRequestMethod(service.method());
                        }

                        String mappingName = null;
                        if (mapping == null) {
                            mappingName = m.getName();
                        } else {
                            mappingName = mapping.value();
                            element.setRequestMethod(mapping.method());
                        }

                        Arrays.asList(m.getParameters()).forEach(p ->

                                {
                                    cn.icodeit.cartman.core.annotation.Param param =
                                            p.getAnnotation(cn.icodeit.cartman.core.annotation.Param.class);


                                    String paramName = null;
                                    boolean required = true;
                                    if (param == null) {
                                        paramName = p.getName();

                                    } else {
                                        paramName = param.value();
                                        required = param.required();
                                    }
                                    ParamElement paramElement = new ParamElement(paramName, p.getType(), required);
                                    element.addParam(paramElement);


                                }

                        );

                        String key = createKey(serviceNameCall, mappingName);

                        put(key, element);


                    }
            );
        }

        );


    }

}
