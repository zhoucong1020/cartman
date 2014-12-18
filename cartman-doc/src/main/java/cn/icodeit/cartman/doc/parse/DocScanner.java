package cn.icodeit.cartman.doc.parse;


import cn.icodeit.cartman.core.service.ActionHandler;
import cn.icodeit.cartman.core.service.ClassScanner;
import cn.icodeit.cartman.doc.provider.DocApiProvider;

import static cn.icodeit.cartman.core.Cartman.handler;
import static cn.icodeit.cartman.core.service.ActionConfiguration.scan;


/**
 * Created by lcf on 2014/11/26.
 */
public class DocScanner {
    public static void main(String[] args) {
        String packageName = "cn.icodeit.cartman.doc.testService";
        scan(packageName);
        handler("/", new ActionHandler());
        DocApiProvider.init(ClassScanner.scan(packageName));
    }
}
