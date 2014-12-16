package cn.icodeit.cartman.core.service;

import cn.icodeit.cartman.core.io.Request;
import cn.icodeit.cartman.core.io.Response;
import cn.icodeit.cartman.core.service.mapping.ActionMapping;

/**
 * @author zhoucong
 * @since 0.0.1
 */
public class ActionContext {

    private Request request;
    private Response response;
    private ActionMapping actionMapping;

    public ActionContext(Request request, Response response, ActionMapping actionMapping) {
        this.request = request;
        this.response = response;
        this.actionMapping = actionMapping;
    }

    public Request getRequest() {
        return request;
    }

    public Response getResponse() {
        return response;
    }

    public ActionMapping getActionMapping() {
        return actionMapping;
    }
}
