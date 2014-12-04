package cn.icodeit.cartman.doc.parse;

import cn.icodeit.cartman.core.annotation.MethodField;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lcf on 2014/12/2.
 */
public class DocTypeFormatter {
    public static Map<String,String> defaultParamTypeMap = new HashMap<>();
   public static Map<String,String> defaultDataTypeMap = new HashMap<>();
    static {
        defaultDataTypeMap.put("byte", "integer");
        defaultDataTypeMap.put("short", "integer");
        defaultDataTypeMap.put("int", "integer");
        defaultDataTypeMap.put("long", "integer");
        defaultDataTypeMap.put("float", "number");
        defaultDataTypeMap.put("double", "number");
        defaultDataTypeMap.put("char", "string");
        defaultDataTypeMap.put("boolean", "boolean");
        defaultDataTypeMap.put("java.lang.Short", "integer");
        defaultDataTypeMap.put("java.lang.Byte", "integer");
        defaultDataTypeMap.put("java.lang.Integer", "integer");
        defaultDataTypeMap.put("java.lang.Long", "integer");
        defaultDataTypeMap.put("java.lang.Float", "number");
        defaultDataTypeMap.put("java.lang.Double", "number");
        defaultDataTypeMap.put("java.lang.Character", "string");
        defaultDataTypeMap.put("java.lang.Boolean", "boolean");
        defaultDataTypeMap.put("java.lang.String", "string");
        defaultDataTypeMap.put("java.util.Date", "string");
        defaultDataTypeMap.put("java.util.DateTime", "string");
        defaultDataTypeMap.put("java.sql.TimeStamp", "string");

        defaultParamTypeMap.put(MethodField.GET.name(),"query");
        defaultParamTypeMap.put(MethodField.POST.name(),"form");
        defaultParamTypeMap.put(MethodField.PUT.name(),"query");
        defaultParamTypeMap.put(MethodField.DELETE.name(),"query");
        defaultParamTypeMap.put("body","body");
    }

    public static  String formatType(String type){
        String str = DocTypeFormatter.defaultDataTypeMap.get(type);
        if(str!=null){
            return  str;
        }else{
            type = type.substring(type.lastIndexOf(".")+1);
        }
        return str;
    }
    public static boolean isJavaClass(Class<?> clz) {
         return clz != null && clz.getClassLoader() == null;
      }

}
