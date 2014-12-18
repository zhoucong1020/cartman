package cn.icodeit.cartman.core.io.server.action;

import cn.icodeit.cartman.core.io.exception.ServiceException;
import cn.icodeit.cartman.core.io.server.ActionContext;

/**
 * @author zhoucong
 */
public class ActionFactory {
    public static Action createAction(ActionContext actionContext) {
        switch (actionContext.mapping().getRequestMethod()) {
            case GET:
                return new GetAction(actionContext);
            case POST:
                return new PostAction(actionContext);
            default:
                throw new ServiceException(405, "unhandled request method " + actionContext.mapping().toString());
        }
    }
}
