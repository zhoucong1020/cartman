package cn.icodeit.cartman.doc.parse;

import cn.icodeit.cartman.core.annotation.mode.AccessElement;
import cn.icodeit.cartman.core.annotation.parse.InitServiceCall;
import cn.icodeit.cartman.doc.view.DocApi;

import java.util.Map;
import java.util.function.BiConsumer;

/**
 * Created by lcf on 2014/11/27.
 */
public class DefaultDocGenerator {
    public DocApi generateDocApi(Map<String,AccessElement> map){
        DocApi docApi = new DocApi();
        map.forEach((str,ele)->{
            
        });
        return docApi;
    }
}
