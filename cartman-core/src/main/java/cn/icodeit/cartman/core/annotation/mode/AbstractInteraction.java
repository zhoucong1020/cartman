package cn.icodeit.cartman.core.annotation.mode;

import cn.icodeit.cartman.core.annotation.Mapping;
import cn.icodeit.cartman.core.annotation.MethodField;
import cn.icodeit.cartman.core.annotation.Service;
import cn.icodeit.cartman.core.annotation.mode.convert.Convert;
import cn.icodeit.cartman.core.io.Request;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static cn.icodeit.cartman.core.annotation.parse.InitServiceCall.*;

/**
 * .
 * User: yuanweifeng
 * Date: 14-11-26
 * Time: 上午9:03
 */
public abstract class AbstractInteraction implements Interaction {

    public String getRequestPrefix(Request request) {
        String uri = request.uri();
        String res = uri.split("\\?")[0];
        return res.trim();
    }

    public AccessElement accessElement(Request request) {
        AccessElement element = get(getRequestPrefix(request));

        if(getRequestMethod(request)!=element.getRequestMethod()){
            throw new IllegalArgumentException("un correct request method ,"
                    +"request is "+ getRequestMethod(request)
                    + " should be " + element.getRequestMethod());
        }
        return element;
    }


    public String execute(Request request, Convert convert) {
        try {

            AccessElement element = accessElement(request);


            Object ret = Location.invoker(element,
                    getParams(request, convert).toArray());

            return convert.stringConvert(ret);
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }

    }


    public MethodField getRequestMethod(Request request) {
        String method = request.requestMethod();
        return MethodField.valueOf(method);
    }


    private List getParams(Request request, Convert convert) {
        List result = new ArrayList();
        AccessElement element = get(getRequestPrefix(request));

        element.getParams().forEach(e -> {

            String attribute = getAttribute(request,e.getAnnotationName(),element.getRequestMethod());
            if (e.isRequired() && attribute == null) {
                throw new IllegalArgumentException
                        ("request parameters less than expected " + e.getAnnotationName());
            }
            if (!e.isRequired() && attribute == null) {
                result.add("");
            }
            result.add(convert.convert(attribute, e.getClassType()));

        });


        return result;

    }

    private String getAttribute(Request request,String annotationName,MethodField methodField){
       if(methodField==MethodField.GET){
           return   request.attribute(annotationName);
       }
        if(methodField==MethodField.POST){
            String body = request.body();

            int i = body.indexOf(annotationName);
            String substring = body.substring(i);
            int i1 = substring.indexOf("&");
            if(i1==-1){
                  return substring.split("=")[1];
            }
            String substring1 = body.substring(i, i1+i);
            return substring1.split("=")[1];

        }
        throw new IllegalArgumentException("un handler request method ");
    }


}
