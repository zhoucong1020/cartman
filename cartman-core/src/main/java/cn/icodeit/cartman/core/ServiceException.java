package cn.icodeit.cartman.core;

/**
 * @author zhoucong
 */
public class ServiceException extends RuntimeException {

    private int code;

    public ServiceException(int code, String message) {
        super(message);

        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
