package cn.icodeit.cartman.core.service.action;

import cn.icodeit.cartman.core.io.Request;
import cn.icodeit.cartman.core.service.ActionContext;

/**
 * @author zhoucong
 */
public class GetAction extends Action {

    public GetAction(ActionContext context) {
        super(context);
    }

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
