package cn.icodeit.cartman.core.annotation.parse;

import cn.icodeit.cartman.core.annotation.mode.Interaction;

import java.io.InputStream;
import java.util.List;

/**
 * .
 * User: yuanweifeng
 * Date: 14-11-19
 * Time: 下午5:06
 */
public class MethodInvoker extends ServiceDispatcherBase implements Interaction{

    @Override
    public List<Object> getParamObject() {
        return null;
    }


    @Override
    public String getMapOfKey() {
        return null;
    }

    @Override
    public void parseIn(InputStream inputStream) {

    }


}
