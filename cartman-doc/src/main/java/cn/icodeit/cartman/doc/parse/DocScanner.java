package cn.icodeit.cartman.doc.parse;


import cn.icodeit.cartman.core.annotation.parse.MethodInvoker;
import cn.icodeit.cartman.doc.provider.DocHandler;
import cn.icodeit.cartman.doc.provider.DocServiceProvider;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static cn.icodeit.cartman.core.annotation.parse.InitRequest.scanner;
import static cn.icodeit.cartman.core.annotation.parse.InitServiceCall.addExcludeKey;
import static cn.icodeit.cartman.core.io.Cartman.addHandler;


/**
 * Created by lcf on 2014/11/26.
 */
public class DocScanner {
    public static String packageName = "cn.lcf.service";

    public static List<String> scan(String packageName, String basePath) {
        final String fPath;
        packageName = packageName.replace(".", File.separator);
        if (null == basePath || "".equals(basePath.trim())) {
            fPath = DocScanner.class.getClassLoader().getResource("").getPath();
        } else {
            fPath = basePath;
        }
        String path = fPath + packageName;
        File file = new File(path);
        final List<String> list = new ArrayList<String>();
        scanFile(file).forEach(str -> {
            list.add(str.substring(fPath.length() - 1, (str.length() - ".class".length())).replace(File.separator, "."));
        });
//        System.out.println(list);
        return list;
    }

    public static List<String> scanFile(File file) {
        List<String> list = new ArrayList<String>();
        if (file.isDirectory()) {
            for (File f : file.listFiles()) {
                list.addAll(scanFile(f));
            }
        } else {
            if (file.getAbsolutePath().endsWith(".class"))
                list.add(file.getAbsolutePath());
        }
        return list;
    }

    public static void main(String[] args) {
        scanner("cn.icodeit.cartman.doc.testService");
        addHandler("/", new MethodInvoker());
        addExcludeKey("/favicon.ico");
        DocServiceProvider.init();
    }
}
