package cn.icodeit.cartman.io.context;

/**
 * @author zhoucong
 * @since 0.0.1
 */
public class ServerContext {
    private boolean ssl;
    private int port;

    public ServerContext(boolean ssl, int port) {
        this.ssl = ssl;
        this.port = port;
    }

    public boolean isSsl() {
        return ssl;
    }

    public int getPort() {
        return port;
    }
}
