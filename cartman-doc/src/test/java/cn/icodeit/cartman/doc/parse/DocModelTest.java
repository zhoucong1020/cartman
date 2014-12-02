package cn.icodeit.cartman.doc.parse;

import cn.icodeit.cartman.core.annotation.mode.convert.JsonConvert;
import cn.icodeit.cartman.doc.view.DocItem;
import cn.icodeit.cartman.doc.view.DocProperty;
import cn.icodeit.cartman.doc.view.Operation;
import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by lcf on 2014/12/2.
 */
public class DocModelTest {
    @Test
    public void getDocModel(){
        Class clazz = Operation.class;
        Arrays.asList(clazz.getDeclaredFields()).forEach(field -> {
            System.out.println(field.getName());
            System.out.println(DocGenerator.getType(field.getType()));
        });
    }

    @Test
    public void test(){
        DocItem docItem = new DocItem();
        docItem.set$ref("rrrrrr");
        System.out.println(docItem);
        System.out.println(JSON.toJSON(docItem));
        System.out.println(JsonConvert.getInstance().stringConvert(docItem));
    }
}
