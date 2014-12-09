package cn.icodeit.cartman.core.annotation.mode.Interaction.requestMethod;

import cn.icodeit.cartman.core.io.Request;

/**
 * .
 * User: yuanweifeng
 * Date: 14-12-8
 * Time: 上午10:49
 */
public class GetType implements RequestMethod {
    @Override
    public String getAttribute(String annotationName, Request request, boolean required) {
        String attribute = request.attribute(annotationName);
        if (required && attribute == null) {
            throw new IllegalArgumentException
                    ("request parameters less than expected " + annotationName);
        }
        return attribute;
    }
}
