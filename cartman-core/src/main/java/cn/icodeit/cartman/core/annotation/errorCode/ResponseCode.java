package cn.icodeit.cartman.core.annotation.errorCode;

/**
 * .
 * User: yuanweifeng
 * Date: 14-11-19
 * Time: 下午3:13
 */
public enum ResponseCode {

    success(200);

    private int statusCode;

    private ResponseCode(int statusCode) {
        this.statusCode = statusCode;
    }

}
