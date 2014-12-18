package cn.icodeit.cartman.core.io.server.action;

import cn.icodeit.cartman.core.io.Request;
import cn.icodeit.cartman.core.io.server.ActionContext;

/**
 * @author zhoucong
 */
public class PostAction extends Action {

    public PostAction(ActionContext context) {
        super(context);
    }

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
