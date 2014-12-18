package cn.icodeit.cartman.doc.parse;

import cn.icodeit.cartman.core.service.annotation.Param;
import cn.icodeit.cartman.core.service.annotation.Service;
import cn.icodeit.cartman.core.service.annotation.ServiceMethod;
import cn.icodeit.cartman.doc.view.*;

import java.lang.reflect.*;
import java.util.*;

public class DocGenerator {

    public static Set<Class> modelNames = new HashSet<>();

    public static DocApi generateDocApi(List<Class<?>> list) {
        modelNames.clear();
        DocApi docApi = new DocApi();
        List services = docApi.getApis();
        list.forEach(clazz -> {
                if (clazz.isAnnotationPresent(Service.class)) {
                    services.add(generateDocService(clazz));
                }
        });
        return docApi;
    }

    public static DocService generateDocService(Class clazz) {
        DocService docService = new DocService();
        modelNames.clear();
        Service service = (Service) clazz.getAnnotation(Service.class);
        if ("".equals(service.value())) {
            String name = clazz.getSimpleName();
            docService.setPath("/" + name.substring(0, 1).toLowerCase() + name.substring(1));
        } else {
            String path = service.value();
            path = path.startsWith("/") ? path : "/"+ path;
            docService.setPath(path);
        }
        docService.setDescription(service.description());
        Arrays.asList(clazz.getDeclaredMethods()).forEach(method -> {
            if (method.getModifiers() == 1) {
                DocMapping docMapping = generateDocMapping(docService, service, method);
                docService.getApis().add(docMapping);
            }
        });
        modelNames.forEach(clz -> {
            if (DocTypeFormatter.isJavaClass(clz)) {
            } else {
                docService.getModels().put(clz.getSimpleName(), getDocModel(clz, docService));
            }
        });
        System.out.println(" models lenth  " + docService.getModels().size());
        return docService;
    }

    public static DocMapping generateDocMapping(DocService docService, Service service, Method method) {
        DocMapping docMapping = new DocMapping();
        if (method.isAnnotationPresent(ServiceMethod.class)) {
            ServiceMethod mapping = method.getAnnotation(ServiceMethod.class);
            docMapping.setDescription(mapping.description());
            if (mapping.value().toString().startsWith("/")) {
                docMapping.setPath(mapping.value());
            } else {
                docMapping.setPath(docService.getPath() + "/" + mapping.value());
            }
            docMapping.setOperations(generateOperations(method, mapping));
        } else {
            docMapping.setDescription(method.getName());
            docMapping.setPath(docService.getPath() + "/" + method.getName());
            docMapping.setOperations(generateOperations(method, service));
        }
        return docMapping;
    }

    public static List<Operation> generateOperations(java.lang.reflect.Method method, Service service) {
        List<Operation> operations = new ArrayList<>();
        Arrays.asList(service.method()).forEach(m -> {
            Operation operation = new Operation();
            setReturnType(method, operation);
            operation.setMethod(m.name());
            operation.setNickname(method.getName());
            operation.setSummary(method.getName());
            operation.setParameters(generateDocParams(method));
            operations.add(operation);
            addModel(method);
        });
        return operations;
    }

    public static List<Operation> generateOperations(java.lang.reflect.Method method, ServiceMethod mapping) {
        List<Operation> operations = new ArrayList<Operation>();
        Arrays.asList(mapping.method()).forEach(m -> {
            Operation operation = new Operation();
            operation.setNickname(method.getName());
            operation.setMethod(m.name());
            setReturnType(method, operation);
            operation.setSummary(mapping.description());
            operation.setParameters(generateDocParams(method));
            operations.add(operation);
            addModel(method);
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
                docParam.setDescription("".equals(param.description().trim()) ? parameter.getName() : param.description());
                docParam.setRequired(param.required());
            } else {
                docParam.setName(parameter.getName());
                docParam.setDescription(parameter.getName());
                docParam.setRequired(true);
            }
            generateType(parameter, docParam);
            docParams.add(docParam);
            addModel(parameter);
        });
        return docParams;
    }

    private static void addModel(Method method) {
        Class clz = method.getReturnType();
        if (DocTypeFormatter.isJavaClass(clz)) {
            Type returnType = method.getGenericReturnType();
            if (returnType instanceof ParameterizedType)/**//* 如果是泛型类型 */ {
                Type[] types = ((ParameterizedType) returnType)
                        .getActualTypeArguments();// 泛型类型列表
                for (Type type : types) {
                    try {
                        modelNames.add(Class.forName(type.getTypeName()));
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
            modelNames.add(clz);
        }
    }

    private static void addModel(Parameter parameter) {
        Class clz = parameter.getType();
        if (DocTypeFormatter.isJavaClass(clz)) {
            Type paramType = parameter.getParameterizedType();
            if (paramType instanceof ParameterizedType) {
                Type[] types = ((ParameterizedType) paramType)
                        .getActualTypeArguments();// 泛型类型列表
                for (Type type : types) {
                    try {
                        modelNames.add(Class.forName(type.getTypeName()));
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
            modelNames.add(clz);
        }
    }

    private static void generateType(Parameter parameter, DocParam docParam) {
        Class clazz = parameter.getType();
        String typeName = parameter.getParameterizedType().getTypeName();
        String type = DocTypeFormatter.defaultDataTypeMap.get(typeName);
        if (type != null && type.length() > 0) {
            docParam.setType(type);
            docParam.setParamType("query");
            return;
        }
        if (DocTypeFormatter.isJavaClass(clazz)) {
            docParam.setType("array");
            docParam.setParamType("body");
            docParam.setItems(new DocItem(getRealType(parameter.getParameterizedType())));
        } else {
            docParam.setType(clazz.getSimpleName());
            docParam.setParamType("body");
        }
    }

    public static DocModel getDocModel(Class clazz, DocService docService) {
        DocModel docModel = new DocModel();
        docModel.setId(clazz.getSimpleName());
        Map<String, Object> properties = new HashMap<>();
        Arrays.asList(clazz.getDeclaredFields()).forEach(field -> {
            Type type = field.getGenericType();
            try {
                if (type instanceof ParameterizedType) {
                    Class clz = Class.forName(listParameterizedType(type).get(0).getTypeName());
                    properties.put(field.getName(), new DocProperty(clz));
                    if(!DocTypeFormatter.isJavaClass(clz)){
                        docService.getModels().put(clz.getSimpleName(),getDocModel(clz,docService));
                    }
                } else {
                    Class clz = field.getType();
                    if (DocTypeFormatter.isJavaClass(clz)) {
                        properties.put(field.getName(), new DocProperty(getType(field)));
                    } else {
                        properties.put(field.getName(), new DocItem(clz.getSimpleName()));
                        docService.getModels().put(clz.getSimpleName(), getDocModel(clz, docService));
                    }
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        docModel.setProperties(properties);
        return docModel;
    }

    public static String getType(Field field) {
        Class clazz = field.getType();

        String typeName = clazz.getName();
        String type = DocTypeFormatter.defaultDataTypeMap.get(typeName);
        if (type != null && type.length() > 0) {
            return type;
        }
        if (DocTypeFormatter.isJavaClass(clazz)) {
            return getRealType(field.getGenericType());
        } else {
            return clazz.getSimpleName();
        }
    }

    public static String getRealType(Type type) {
        if ("void".equals(type)) {
            return "void";
        }
        List<Type> types = listParameterizedType(type);
        if (types.size() > 0) {
            try {
                String typeName = types.get(0).getTypeName();
                Class clazz = Class.forName(typeName);
                modelNames.add(clazz);
                return clazz.getSimpleName();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return "array";
    }

    public static List<Type> listParameterizedType(Type type) {
        List<Type> list = new ArrayList<>();
        if (type instanceof ParameterizedType) {
            Type[] types = ((ParameterizedType) type)
                    .getActualTypeArguments();// 泛型类型列表
            for (Type type1 : types) {
                list.add(type1);
            }
        }
        return list;
    }

    private static void setReturnType(Method method, Operation operation) {
        operation.setType(method.getReturnType().getSimpleName());
        Type type = method.getGenericReturnType();
        List<Type> types = listParameterizedType(type);
        if (types.size() > 0) {
            operation.setType("array");
            try {
                operation.setItems(new DocItem(Class.forName(types.get(0).getTypeName()).getSimpleName()));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

    }
}
