package cn.icodeit.cartman.doc.parse;

import cn.icodeit.cartman.core.utils.ClassScanUtils;
import cn.icodeit.cartman.doc.provider.DocApiProvider;

import static cn.icodeit.cartman.core.Cartman.scan;


/**
 * Created by lcf on 2014/11/26.
 */
public class DocScanner {
    public static void main(String[] args) {
        scan("cn.icodeit.cartman.doc.testService");
        DocApiProvider.init(ClassScanUtils.scan("cn.icodeit.cartman.doc.testService"));
    }
}
