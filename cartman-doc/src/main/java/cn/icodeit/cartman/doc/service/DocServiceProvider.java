package cn.icodeit.cartman.doc.service;

import cn.icodeit.cartman.core.annotation.Mapping;
import cn.icodeit.cartman.core.annotation.Service;
import cn.icodeit.cartman.core.annotation.errorCode.ResponseCode;
import cn.icodeit.cartman.doc.parse.DocGenerator;
import cn.icodeit.cartman.doc.parse.DocScanner;
import cn.icodeit.cartman.doc.view.DocApi;
import cn.icodeit.cartman.doc.view.DocService;

/**
 * Created by lcf on 2014/11/26.
 */
@Service(value = "doc.json")
public class DocServiceProvider {
    DocApi docApi = null;
    public  DocServiceProvider() {
        docApi = DocGenerator.generateDocApi(DocScanner.scan("cn.icodeit.cartman", ""));
    }

    @Mapping(value = "",status = ResponseCode.success)
    public DocApi all(){
        return docApi;
    }
    @Mapping(value = "doc.json",status = ResponseCode.success)
    public DocService doc1(){
        return docApi.getApis().get(0);
    }

}
