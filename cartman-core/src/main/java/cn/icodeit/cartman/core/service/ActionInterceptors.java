package cn.icodeit.cartman.core.service;


import java.util.ArrayList;
import java.util.List;

/**
 * .
 * User: yuanweifeng
 * Date: 14-12-17
 * Time: 上午9:42
 */
public class ActionInterceptors {
    private List<ActionInterceptor> interceptors = new ArrayList<>();

    public void addAInterceptor(ActionInterceptor iInterceptor) {
        interceptors.add(iInterceptor);
    }

    public boolean isEmptyInterceptor() {
        return interceptors.isEmpty();
    }

    public List<ActionInterceptor> getInterceptors() {
        return interceptors;
    }

}
