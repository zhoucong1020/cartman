package cn.icodeit.cartman.core.service;

import cn.icodeit.cartman.core.io.AbstractHandler;
import cn.icodeit.cartman.core.io.Request;
import cn.icodeit.cartman.core.io.Response;
import cn.icodeit.cartman.core.service.annotation.RequestMethod;
import cn.icodeit.cartman.core.service.mapping.ActionMapping;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import static cn.icodeit.cartman.core.service.ActionConfiguration.getMapping;

/**
 * @author zhoucong
 * @since 0.0.1
 */
public class ActionHandler extends AbstractHandler {

    private ActionExecutor actionExecutor = new ActionExecutor();

    private ActionInterceptors actionInterceptors = new ActionInterceptors();

    public ActionHandler() {
        actionExecutor.init();
    }

    public void addAInterceptor(ActionInterceptor interceptor) {
        actionInterceptors.addAInterceptor(interceptor);
    }

    public void stop() {
        actionExecutor.stop();
    }

    public String getUri(Request request) {
        String uri = request.uri();
        String res = uri.split("\\?")[0];
        return res.trim().substring(1);
    }

    public RequestMethod getRequestMethod(Request request) {
        return RequestMethod.valueOf(request.requestMethod());
    }

    @Override
    public void handle(Request request, Response response) {
        try {
            ActionMapping mapping = getMapping(getUri(request), getRequestMethod(request));
            ActionContext context = createContext(request, response, mapping);
            if(actionInterceptors.isEmptyInterceptor()){
                actionExecutor.submit(context);
            }else {
                actionExecutor.submit(context,actionInterceptors);
            }

        } catch (ServiceException e) {
            response.status(e.getCode());
            response.body(getStackTrace(e));
        } catch (Exception e) {
            response.status(500);
            response.body(getStackTrace(e));
        }
    }

    private ActionContext createContext(Request request, Response response, ActionMapping mapping) {
        ActionContext context = new ActionContext(request, response, mapping);

        return context;
    }

    private String getStackTrace(Throwable throwable) {
        final Writer result = new StringWriter();
        final PrintWriter printWriter = new PrintWriter(result);
        throwable.printStackTrace(printWriter);
        return result.toString();
    }
}
