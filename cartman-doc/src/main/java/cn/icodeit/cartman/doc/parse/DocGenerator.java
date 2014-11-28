package cn.icodeit.cartman.doc.parse;

import cn.icodeit.cartman.core.annotation.Mapping;
import cn.icodeit.cartman.core.annotation.Param;
import cn.icodeit.cartman.core.annotation.Service;
import cn.icodeit.cartman.doc.view.*;

import java.util.*;

/**
 * Created by lcf on 2014/11/27.
 */
public class DocGenerator {

    public static Set<String> modelStrs = new HashSet<>();

    public static List<Operation> generateOperations(java.lang.reflect.Method method, Service service) {
        List<Operation> operations = new ArrayList<>();
        Arrays.asList(service.method()).forEach(m -> {
            Operation operation = new Operation();
            operation.setType(method.getReturnType().getTypeName());
            operation.setMethod(m.name());
            operation.setSummary(method.getName());
            operation.setParameters(generateDocParams(method));
            operations.add(operation);

            modelStrs.add(method.getReturnType().getTypeName());
        });
        return operations;
    }

    public static List<Operation> generateOperations(java.lang.reflect.Method method, Mapping mapping) {
        List<Operation> operations = new ArrayList<Operation>();
        Arrays.asList(mapping.method()).forEach(m -> {
            Operation operation = new Operation();
            operation.setMethod(m.name());
            operation.setType(method.getReturnType().getTypeName());
            operation.setSummary(mapping.description());
            operation.setParameters(generateDocParams(method));
            operations.add(operation);
            modelStrs.add(method.getReturnType().getTypeName());
        });
        return operations;
    }

    private static List<DocParam> generateDocParams(java.lang.reflect.Method method) {
        List<DocParam> docParams = new ArrayList<DocParam>();
        Arrays.asList(method.getParameters()).forEach(parameter -> {
            DocParam docParam = new DocParam();
            if (parameter.isAnnotationPresent(Param.class)) {
                Param param = parameter.getAnnotation(Param.class);
                docParam.setName(param.value());
                docParam.setDescription(param.description());
                docParam.setRequired(param.required());
                docParam.setType(parameter.getParameterizedType().getTypeName());
            } else {
                docParam.setName(parameter.getName());
                docParam.setRequired(false);
                docParam.setType(parameter.getParameterizedType().getTypeName());
            }
            docParams.add(docParam);
            modelStrs.add(docParam.getType());
        });
        return docParams;
    }

    public static DocApi generateDocApi(List<String> list) {
        modelStrs.clear();
        DocApi docApi = new DocApi();
        List services = docApi.getApis();
        list.forEach(str -> {
            try {
                Class clazz = Class.forName(str);
                if (clazz.isAnnotationPresent(Service.class)) {
                    DocService docService = new DocService();
                    Service service = (Service) clazz.getAnnotation(Service.class);
                    if ("".equals(service.value())) {
                        String name = clazz.getSimpleName();
                        docService.setPath("/" + name.substring(0, 1).toLowerCase() + name.substring(1));
                    } else {
                        docService.setPath(service.value());
                    }
                    docService.setDescription(service.description());
                    services.add(docService);
                    Arrays.asList(clazz.getDeclaredMethods()).forEach(method -> {
                        if (method.getModifiers() == 1) {
                            DocMapping docMapping = new DocMapping();
                            if (method.isAnnotationPresent(Mapping.class)) {
                                Mapping mapping = method.getAnnotation(Mapping.class);
                                docMapping.setDescription(mapping.description());
                                if (mapping.value().toString().startsWith("/")) {
                                    docMapping.setPath(mapping.value());
                                } else {
                                    docMapping.setPath(docService.getPath() + "/" + mapping.value());
                                }
                                docMapping.setOperationses(generateOperations(method, mapping));
                            } else {
                                docMapping.setDescription(method.getName());
                                docMapping.setPath(docService.getPath() + "/" + method.getName());
                                docMapping.setOperationses(generateOperations(method, service));
                            }
                            docService.getDocMappings().add(docMapping);
                        }
                    });
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        return docApi;
    }
}
