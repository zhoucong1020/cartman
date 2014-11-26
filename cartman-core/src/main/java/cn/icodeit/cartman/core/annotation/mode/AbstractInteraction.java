package cn.icodeit.cartman.core.annotation.mode;

import cn.icodeit.cartman.core.annotation.Mapping;
import cn.icodeit.cartman.core.annotation.MethodField;
import cn.icodeit.cartman.core.annotation.Service;
import cn.icodeit.cartman.core.annotation.mode.convert.Convert;
import cn.icodeit.cartman.core.annotation.mode.convert.JsonConvert;
import cn.icodeit.cartman.core.boot.ServerBootLoader;
import cn.icodeit.cartman.core.io.Request;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static cn.icodeit.cartman.core.annotation.parse.InitServiceCall.*;

/**
 * .
 * User: yuanweifeng
 * Date: 14-11-26
 * Time: 上午9:03
 */
public abstract class AbstractInteraction implements Interaction {

    private static String packageName = "";

    public static void scanner(String annotationPackage) {

        packageName = annotationPackage;
        File file = new File(packageName);
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
                String s = packageName + "/" + e.replace(".java", "");
                String path = ServerBootLoader.class.getClassLoader().getResource("").getPath();
                String replace = s.replace(path, "");
                String replace1 = replace.replace("/", ".");
                String replace2 = replace1.replace(".class", "");
                aClass = Class.forName(replace2);
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }

            Service service = (Service) aClass.getAnnotation(Service.class);

            String serviceName = null;
            if (service == null) {
                serviceName = aClass.getSimpleName();
            }else{
                serviceName = service.value();

            }
            Class classEl = aClass;
            String serviceNameCall = serviceName;
            Arrays.asList(aClass.getMethods()).forEach(m ->
                    {
                        Mapping mapping = m.getAnnotation(Mapping.class);

                        AccessElement element = new AccessElement(classEl, m);


                        String mappingName = null;
                        if (mapping == null) {
                            mappingName = m.getName();
                        }else {
                            mappingName = mapping.value();
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
                        if (!isContainKey(key))
                            put(key, element);


                    }
            );
        }

        );


    }

    public String getRequestPrefix(Request request) {
        String uri = request.uri();
        return uri.split("/?")[0];
    }


    public String execute(Request request) {
        Convert instance = JsonConvert.getInstance();
        AccessElement element = get(getRequestPrefix(request));
        Object invoker = Location.invoker(element.getClazz(),
                element.getMethod(), getParams(request, instance));
        return instance.StringConvert(invoker);
    }

    @Override
    public MethodField getRequestMethod(Request request) {
        String method = request.requestMethod();
        return MethodField.valueOf(method);
    }

    @Override
    public List<Object> getParams(Request request, Convert convert) {
        List<Object> result = new ArrayList<>();
        AccessElement element = get(getRequestPrefix(request));
        element.getParams().forEach(e -> {

            String attribute = request.attribute(e.getAnnotationName());
            if (!e.isRequired() && attribute == null) {
                result.add("");
            }
            result.add(convert.convert(attribute, e.getClassType()));

        });
        return result;
    }
}
