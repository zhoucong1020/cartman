package cn.icodeit.cartman.core.annotation.errorCode;

import cn.icodeit.cartman.core.annotation.parse.InitServiceCall;

/**
 * .
 * User: yuanweifeng
 * Date: 14-12-1
 * Time: 下午5:11
 */
public enum ResponseCode {
    success,
    NOT_FOUND,
    PERMISSION_401,
    INTERNAL_SERVER_ERROR_500;


    public ResponseCode record(String msg) {
        InitServiceCall.record(this, msg);
        return this;
    }
}
