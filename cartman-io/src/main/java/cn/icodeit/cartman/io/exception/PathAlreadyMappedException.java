package cn.icodeit.cartman.io.exception;

/**
 * @author zhoucong
 */
public class PathAlreadyMappedException extends RuntimeException {

    public PathAlreadyMappedException(String path) {
        super("Path '" + path + "' already mapped by another handler.");
    }
}
