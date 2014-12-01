package cn.icodeit.cartman.doc.provider;

import cn.icodeit.cartman.core.annotation.parse.InitServiceCall;
import cn.icodeit.cartman.core.io.Cartman;
import cn.icodeit.cartman.doc.parse.DocGenerator;
import cn.icodeit.cartman.doc.parse.DocScanner;
import cn.icodeit.cartman.doc.view.DocApi;
import cn.icodeit.cartman.doc.view.DocMapping;
import cn.icodeit.cartman.doc.view.DocService;

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
        Cartman.addHandler("/doc.json",new DocHandler<DocApi>(docApi));
        docApi.getApis().forEach(docService ->{
            docService.setApiVersion(docApi.getApiVersion());
            docService.setBasePath("/");
            docService.setResourcePath(docApi.getResourcePath());
            String path = docService.getPath();
            if(path.startsWith("/")){
                path = path.substring(1);
            }
            Cartman.addHandler("/doc.json/" + path, new DocHandler<DocService>(docService));
        });
    }
}
