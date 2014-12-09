package cn.icodeit.cartman.core.annotation.mode.Interaction.requestMethod;

import cn.icodeit.cartman.core.io.Request;

/**
 * .
 * User: yuanweifeng
 * Date: 14-12-8
 * Time: 上午10:52
 */
public interface RequestMethod {

    String getAttribute(String annotationName, Request request, boolean required);

}
