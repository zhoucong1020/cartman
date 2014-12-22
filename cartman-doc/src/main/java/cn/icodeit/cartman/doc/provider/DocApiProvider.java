package cn.icodeit.cartman.doc.provider;

import cn.icodeit.cartman.core.Cartman;
import cn.icodeit.cartman.doc.parse.DocGenerator;
import cn.icodeit.cartman.doc.parse.DocTypeFormatter;
import cn.icodeit.cartman.doc.view.DocApi;
import cn.icodeit.cartman.doc.view.DocItem;
import cn.icodeit.cartman.doc.view.DocService;

import java.util.List;

/**
 * Created by lcf on 2014/11/26.
 */
public class DocApiProvider {
    static DocApi docApi = null;

    public DocApiProvider() {
    }

    public static void init(List<Class<?>> list) {
        docApi = DocGenerator.generateDocApi(list);
        Cartman.get("/doc.json", new DocHandler<>(docApi));
        docApi.getApis().forEach(docService -> {
            docService.setApiVersion(docApi.getApiVersion());
            docService.setBasePath("/");
            docService.setResourcePath(docApi.getResourcePath());
            String path = docService.getPath();
            if (path.startsWith("/")) {
                path = path.substring(1);
            }

            Cartman.get("/doc.json/" + path, new DocHandler<>(docService));
            docService.getApis().forEach(docMapping -> {
                docMapping.getOperations().forEach(operation -> {
                    operation.getParameters().forEach(docParam -> {
                        docParam.setParamType(DocTypeFormatter.generateParamType(docParam.getParamType(), operation.getMethod()));
                    });
                });
            });
        });
        docApi.getApis().get(0).getApis().get(0).getOperations().forEach(operation -> {
            operation.getParameters().forEach(parameter -> {
                DocItem docItem = parameter.getItems();
                if (docItem != null) {
                    System.out.println(docItem.get$ref());
                }
            });
        });
    }

    public static DocApi docApi() {
        return docApi;
    }

    public static DocService docService(String serviceName) {
        DocService result = null;
        for (DocService docService : docApi().getApis()) {
            if (serviceName.equals(docService.getPath())) {
                result = docService;
                break;
            }
        }
        return result;
    }
}
