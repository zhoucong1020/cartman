package cn.icodeit.cartman.core.service.action;

import cn.icodeit.cartman.core.service.ActionContext;
import cn.icodeit.cartman.core.service.ServiceException;

/**
 * @author zhoucong
 */
public class ActionFactory {
    public static Action createAction(ActionContext actionContext) {
        switch (actionContext.getActionMapping().getRequestMethod()) {
            case GET:
                return new GetAction(actionContext);
            case POST:
                return new PostAction(actionContext);
            default:
                throw new ServiceException(405, "unhandled request method " + actionContext.getActionMapping().toString());
        }
    }
}
