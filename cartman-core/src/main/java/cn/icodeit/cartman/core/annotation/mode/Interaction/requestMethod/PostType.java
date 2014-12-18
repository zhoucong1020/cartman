package cn.icodeit.cartman.core.annotation.mode.Interaction.requestMethod;

import cn.icodeit.cartman.core.io.Request;

/**
 * .
 * User: yuanweifeng
 * Date: 14-12-8
 * Time: 上午10:49
 */
public class PostType implements RequestMethod {
    @Override
    public String getAttribute(String annotationName, Request request, boolean required) {
        String body = request.body();

        int i = body.indexOf(annotationName + "=");
        String substring = body.substring(i);
        int i1 = substring.indexOf("&");
        if (i1 == -1) {
            return substring.split("=")[1];
        }
        String substring1 = body.substring(i, i1 + i);
        String attribute = substring1.split("=")[1];
        if (required && attribute == null) {
            throw new IllegalArgumentException
                    ("request parameters less than expected " + annotationName);
        }
        return attribute;

    }
}
