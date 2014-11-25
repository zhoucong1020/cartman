package cn.icodeit.cartman.core.annotation.parse;

import cn.icodeit.cartman.core.annotation.Mapping;
import cn.icodeit.cartman.core.annotation.Service;
import cn.icodeit.cartman.core.annotation.mode.AccessElement;
import cn.icodeit.cartman.core.annotation.mode.ParamElement;

import java.io.File;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * .
 * User: yuanweifeng
 * Date: 14-11-19
 * Time: 下午2:25
 */
public abstract class ServiceDispatcherBase {

    private static Map<String, AccessElement> requestLocation =
            new HashMap<>();

    static {
        scanService("");
    }

    public abstract String getMapOfKey();

    private static void scanService(String pkgName) {
        File file = new File(pkgName);
        if (!file.isDirectory()) {
            throw new IllegalArgumentException("service package name error, it is a file not a folder!");

        }

        Arrays.asList(file.list()).forEach(e -> {
            Class aClass = null;
            try {
                aClass = Class.forName(pkgName + "/" + e);
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }

            Service service = (Service) aClass.getAnnotation(Service.class);

            String serviceName = null;
            if (service == null) {
                serviceName = aClass.getSimpleName();
            }
            serviceName = service.value();

            Class classEl = aClass;
            String serviceNameCall = serviceName;
            Arrays.asList(aClass.getMethods()).forEach(m ->
                    {
                        Mapping mapping = m.getAnnotation(Mapping.class);

                        AccessElement element = new AccessElement(classEl, m);


                        String mappingName = null;
                        if (mapping == null) {
                            mappingName = m.getName();
                        }
                        mappingName = mapping.value();

                        Arrays.asList(m.getParameters()).forEach(p ->

                                {
                                    cn.icodeit.cartman.core.annotation.Param param =
                                            p.getAnnotation(cn.icodeit.cartman.core.annotation.Param.class);


                                    String paramName = null;
                                    boolean optional = true;
                                    if (param == null) {
                                        paramName = p.getName();

                                    } else {
                                        paramName = param.value();
                                        optional = param.required();
                                    }
                                    ParamElement paramElement = new ParamElement(paramName, p.getType(), optional);
                                    element.addParam(paramElement);


                                }

                        );
                        String key = serviceNameCall + "/" + mappingName;
                        if (requestLocation.get(key) != null) {
                            throw new IllegalArgumentException("Access repeat define !" + key);
                        }
                        requestLocation.put(key, element);


                    }
            );
        }

        );

    }

    public abstract void parseIn(InputStream inputStream);

}
