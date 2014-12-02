package cn.icodeit.cartman.doc.provider;

import cn.icodeit.cartman.core.annotation.mode.convert.JsonConvert;
import cn.icodeit.cartman.core.annotation.parse.InitServiceCall;
import cn.icodeit.cartman.core.io.Cartman;
import cn.icodeit.cartman.doc.parse.DocGenerator;
import cn.icodeit.cartman.doc.parse.DocScanner;
import cn.icodeit.cartman.doc.parse.DocTypeFormatter;
import cn.icodeit.cartman.doc.view.*;

import java.lang.reflect.Parameter;

/**
 * Created by lcf on 2014/11/26.
 */
public class DocServiceProvider {
    static  DocApi  docApi = null;
    public  DocServiceProvider() {
    }
    public  static void init(){
        docApi =DocGenerator.generateDocApi(InitServiceCall.requestLocation);
//        docApi = DocGenerator.generateDocApi(DocScanner.scan("cn.icodeit.cartman", ""));
        Cartman.addHandler("/doc.json", new DocHandler<DocApi>(docApi));
        docApi.getApis().forEach(docService ->{
            docService.setApiVersion(docApi.getApiVersion());
            docService.setBasePath("/");
            docService.setResourcePath(docApi.getResourcePath());
            String path = docService.getPath();
            if(path.startsWith("/")){
                path = path.substring(1);
            }
            Cartman.addHandler("/doc.json/" + path, new DocHandler<DocService>(docService));

            docService.getApis().forEach(docMapping->{
                docMapping.getOperations().forEach(operation->{
                    operation.getParameters().forEach(docParam->{
                        docParam.setParamType(generateParamType(docParam.getParamType(),operation.getMethod()));
                    });
                });
            });
        });
        docApi.getApis().get(0).getApis().get(0).getOperations().forEach(operation->{
            operation.getParameters().forEach(parameter ->{
                DocItem docItem = parameter.getItems();
                if(docItem !=null){
                    System.out.println(docItem.get$ref());
                }
            });
        });
        System.out.println(JsonConvert.getInstance().stringConvert(docApi));
    }

    private static String generateParamType(String paramType,String method) {
        if(paramType.equals(DocTypeFormatter.defaultParamTypeMap.get("body"))){
            return  paramType;
        }else{
            return DocTypeFormatter.defaultParamTypeMap.get(method) ;
        }
    }
}
