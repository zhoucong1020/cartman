package cn.icodeit.cartman.core;

/**
 * @author zhoucong
 * @since 0.0.1
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
