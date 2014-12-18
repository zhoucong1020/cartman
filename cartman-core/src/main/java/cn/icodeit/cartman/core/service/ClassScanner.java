package cn.icodeit.cartman.core.service;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * @author zhoucong
 * @since 0.0.1
 */
public enum ClassScanner {
    ;

    public static List<Class<?>> scan(String packageName) {
        List<Class<?>> classes = new ArrayList<>();

        String packagePath = packageName.replace(".", "/");

        try {
            Enumeration<URL> dirs = Thread.currentThread().getContextClassLoader().getResources(packagePath);
            while (dirs.hasMoreElements()) {
                URL url = dirs.nextElement();
                if (url.getProtocol().equals("file")) {
                    String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
                    classes.addAll(scanPath(filePath, packageName));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return classes;
    }

    private static List<Class<?>> scanPath(String filePath, String packageName) {
        List<Class<?>> classes = new ArrayList<>();

        File path = new File(filePath);
        if (!path.exists()) {
            return classes;
        }

        File[] files = path.listFiles(e -> e.isDirectory() || e.getName().endsWith(".class"));
        for (File file : files) {
            if (file.isDirectory()) {
                scanPath(filePath + "/" + file.getName(), packageName + "." + file.getName());
            } else {
                Class<?> clazz = getClass(packageName + "." + file.getName().substring(0, file.getName().length() - 6));
                if (clazz != null) {
                    classes.add(clazz);
                }
            }
        }

        return classes;
    }

    private static Class<?> getClass(String className) {
        try {
            return Thread.currentThread().getContextClassLoader().loadClass(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }
}
