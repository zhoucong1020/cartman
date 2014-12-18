package cn.icodeit.cartman.core.io.server;

import cn.icodeit.cartman.core.io.Request;
import cn.icodeit.cartman.core.io.exception.ServiceException;
import cn.icodeit.cartman.core.io.server.action.ActionExecutor;
import cn.icodeit.cartman.core.service.annotation.RequestMethod;
import cn.icodeit.cartman.core.service.mapping.ActionMapping;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import static cn.icodeit.cartman.core.Cartman.getMapping;

/**
 * @author zhoucong
 * @since 0.0.1
 */
public class CartmanActionHandler extends CartmanServerRequestHandler {

    private ActionExecutor actionExecutor = new ActionExecutor();

    public CartmanActionHandler() {
        actionExecutor.init();
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
    public void handle(ActionContext ctx) {
        try {
            ActionMapping mapping = getMapping(getUri(ctx.request()), getRequestMethod(ctx.request()));
            ctx.mapping(mapping);
            actionExecutor.submit(ctx);
        } catch (ServiceException e) {
            ctx.response().status(e.getCode());
            ctx.response().body(getStackTrace(e));
            ctx.flush();
        } catch (Exception e) {
            ctx.response().status(500);
            ctx.response().body(getStackTrace(e));
            ctx.flush();
        }
    }

    private String getStackTrace(Throwable throwable) {
        final Writer result = new StringWriter();
        final PrintWriter printWriter = new PrintWriter(result);
        throwable.printStackTrace(printWriter);
        return result.toString();
    }
}
