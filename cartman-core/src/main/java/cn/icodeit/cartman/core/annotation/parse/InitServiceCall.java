package cn.icodeit.cartman.core.annotation.parse;

import cn.icodeit.cartman.core.annotation.MethodField;
import cn.icodeit.cartman.core.annotation.errorCode.ResponseCode;
import cn.icodeit.cartman.core.annotation.mode.AccessElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * .
 * User: yuanweifeng
 * Date: 14-11-19
 * Time: 下午2:25
 */
public class InitServiceCall {

    public static Map<String, List<AccessElement>> requestLocation = new HashMap<>();

    private static List<String> excludeKeys = new ArrayList<>();

    private static Map<String, ResponseCode> responseCodeMap = new HashMap<>();


    public static void put(String key, AccessElement value) {
        List<AccessElement> elements = requestLocation.get(key);
        if(elements==null){
            elements = new ArrayList<>(4);
            elements.add(value);
            requestLocation.put(key,elements);
            return;
        }
        if(!elements.contains(value)){
            elements.forEach(e->{
                if(e.getRequestMethod().equals(value.getRequestMethod()))
                    throw new IllegalArgumentException
                            (" init request :" + key + " method: "+value+ " error! " +
                            value.getRequestMethod() +" has already defined !");
            });
            elements.add(value);
        }
    }

    public static void record(ResponseCode code, String msg) {
        if (!responseCodeMap.containsKey(msg)) {
            responseCodeMap.put(msg, code);
            writeWorkError();
        }
    }


    public static boolean isContainKey(String key) {
        return requestLocation.get(key) != null;
    }

    private static void unDefineRequestCheck(String key) {
        if (!isExcludeKey(key) && !isContainKey(key)) {
            throw new IllegalAccessError("unDefine request :" + key);
        }
    }

    private static void excludeRequestCheck(String key) {
        if (isExcludeKey(key)) {
            throw new IllegalArgumentException("exclude request :" + key);
        }
    }

    public static AccessElement get(String key,MethodField field)
            throws IllegalAccessError, IllegalArgumentException {
        excludeRequestCheck(key);
        unDefineRequestCheck(key);

        List<AccessElement> elements = requestLocation.get(key);
        for(AccessElement e: elements){
            if(e.getRequestMethod().equals(field)){
                return e;
            }
        }
        throw new IllegalArgumentException(" request :" + key + " unDefine method: "+ field);


    }

    public static String createKey(String serviceName, String mappingName) {
        String fullKey = serviceName + "/" + mappingName;
        return fullKey.trim();
    }

    public static void addExcludeKey(String key) {
        if (!isExcludeKey(key)) {
            excludeKeys.add(key);
        }
    }

    public static boolean isExcludeKey(String key) {
        return excludeKeys.contains(key);
    }

    public static void writeWorkError() {
        responseCodeMap.entrySet().forEach(e -> {
            String key = e.getKey();
            ResponseCode value = e.getValue();
            System.out.println("key: " + key + " valueCode: " + value);
        });
    }

}
