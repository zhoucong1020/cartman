package cn.icodeit.cartman.core.annotation.mode.Interaction;

import cn.icodeit.cartman.core.annotation.MethodField;
import cn.icodeit.cartman.core.annotation.mode.AccessElement;
import cn.icodeit.cartman.core.annotation.mode.convert.Convert;
import cn.icodeit.cartman.core.io.Request;

import java.util.ArrayList;
import java.util.List;

import static cn.icodeit.cartman.core.annotation.parse.InitServiceCall.get;

/**
 * .
 * User: yuanweifeng
 * Date: 14-11-26
 * Time: 上午9:03
 */
public abstract class AbstractInteraction implements Interaction {

    @Override
    public String getRequestPrefix(Request request) {
        String uri = request.uri();
        String res = uri.split("\\?")[0];
        return res.trim();
    }

    @Override
    public String execute(Request request, Convert convert) {
        try {
            AccessElement element = get(getRequestPrefix(request));
            Object ret = Location.invoker(element, getParams(request, convert).toArray());

            return convert.stringConvert(ret);
        } catch (IllegalArgumentException e) {
            //return error request info
            return e.getMessage();
        }

    }

    @Override
    public MethodField getRequestMethod(Request request) {
        String method = request.requestMethod();
        return MethodField.valueOf(method);
    }


    private List getParams(Request request, Convert convert) {
        List result = new ArrayList();
        AccessElement element = get(getRequestPrefix(request));
        checkRequestMethod(request, element);
        element.getParams().forEach(e -> {

            String attribute = getAttribute(request, e.getAnnotationName(), element.getRequestMethod());
            if (e.isRequired() && attribute == null) {
                throw new IllegalArgumentException
                        ("request parameters less than expected " + e.getAnnotationName());
            }
            // if (!e.isRequired() && attribute == null) {
            //   attribute="";
            //}
            result.add(convert.convert(attribute, e.getClassType()));

        });


        return result;

    }

    private void checkRequestMethod(Request request, AccessElement element) {
        if (getRequestMethod(request) != element.getRequestMethod()) {
            throw new IllegalArgumentException("un correct request method ,"
                    + "request is " + getRequestMethod(request)
                    + " should be " + element.getRequestMethod());
        }

    }

    private String getAttribute(Request request, String annotationName, MethodField methodField) {
        if (methodField == MethodField.GET) {
            return request.attribute(annotationName);
        }
        if (methodField == MethodField.POST) {
            String body = request.body();

            int i = body.indexOf(annotationName);
            String substring = body.substring(i);
            int i1 = substring.indexOf("&");
            if (i1 == -1) {
                return substring.split("=")[1];
            }
            String substring1 = substring.substring(i, i1);
            return substring1.split("=")[1];

        }
        throw new IllegalArgumentException("un handler request method ");
    }


}
