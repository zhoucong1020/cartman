package cn.icodeit.cartman.core.io;

/**
 * @author zhoucong
 */
public interface HandlerContainer {

    public Handler getHandler(Request request);
}
