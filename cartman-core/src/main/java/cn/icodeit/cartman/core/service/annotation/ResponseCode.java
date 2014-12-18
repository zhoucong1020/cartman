package cn.icodeit.cartman.core.service.annotation;

/**
 * @author zhoucong
 */
public enum ResponseCode {
    success,
    NOT_FOUND,
    PERMISSION_401,
    INTERNAL_SERVER_ERROR_500;


    public ResponseCode record(String msg) {
        return this;
    }
}
