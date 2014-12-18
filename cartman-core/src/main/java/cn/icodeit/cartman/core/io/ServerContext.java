package cn.icodeit.cartman.core.io;

/**
 * @author zhoucong
 * @since 0.0.1
 */
public class ServerContext {

    private static boolean _ssl = false;
    private static int _port = 8080;

    public static boolean ssl() {
        return _ssl;
    }

    public static void ssl(boolean ssl) {
        _ssl = ssl;
    }

    public static int port() {
        return _port;
    }

    public static void port(int port) {
        _port = port;
    }
}
