package cn.icodeit.cartman.doc.parse;

import cn.icodeit.cartman.core.annotation.mode.convert.JsonConvert;
import cn.icodeit.cartman.doc.testService.ServiceA;
import cn.icodeit.cartman.doc.view.DocItem;
import cn.icodeit.cartman.doc.view.DocProperty;
import cn.icodeit.cartman.doc.view.Operation;
import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

/**
 * Created by lcf on 2014/12/2.
 */
public class DocModelTest {
    @Test
    public void getDocModel() {
        Class clazz = Operation.class;
        Arrays.asList(clazz.getDeclaredFields()).forEach(field -> {
            System.out.println(field.getName());
            System.out.println(DocGenerator.getType(field));
        });
    }

    @Test
    public void testJSON() {
        DocItem docItem = new DocItem();
        docItem.set$ref("rrrrrr");
        System.out.println(docItem);
        System.out.println(JSON.toJSON(docItem));
        System.out.println(JsonConvert.getInstance().stringConvert(docItem));
    }
    @Test
      public void testMethodReturnType() throws NoSuchMethodException {
       Method method =  DocModelTest.class.getMethod("testJSON");
       String str = DocGenerator.getRealType(method.getGenericReturnType());
        System.out.println(method.getGenericReturnType().getTypeName());
    }

    @Test
    public void testMethod() throws NoSuchMethodException {
        Method method = ServiceA.class.getMethod("list", List.class);
        System.out.println(method.getGenericReturnType().getTypeName());
        Type returnType = method.getGenericReturnType();
        if (returnType instanceof ParameterizedType)/**//* 如果是泛型类型 */ {
            Type[] types = ((ParameterizedType) returnType)
                    .getActualTypeArguments();// 泛型类型列表
            System.out.println(" Return TypeArgument: ");
            for (Type type : types) {
                System.out.println("typeName " + type.getTypeName());
            }
        }

        Type[] paramTypeList = method.getGenericParameterTypes();// 方法的参数列表
        for (Type paramType : paramTypeList) {
            System.out.println("  " + paramType);// 参数类型
            if (paramType instanceof ParameterizedType)/**//* 如果是泛型类型 */ {
                Type[] types = ((ParameterizedType) paramType)
                        .getActualTypeArguments();// 泛型类型列表
                System.out.println(" Parameter TypeArgument: ");
                for (Type type : types) {
                    System.out.println("   " + type);
                }
            }
        }


        System.out.println(method.getName());
    }
}
