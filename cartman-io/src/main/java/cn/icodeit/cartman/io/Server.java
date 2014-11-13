package cn.icodeit.cartman.io;

import cn.icodeit.cartman.io.context.ServerContext;

import javax.net.ssl.SSLException;
import java.security.cert.CertificateException;

/**
 * @author zhoucong
 * @since 0.0.1
 */
public interface Server {
    /**
     * 异步方式启动服务器
     * @param context 服务器启动参数配置上下文
     */
    void start(ServerContext context) throws CertificateException, SSLException;
}
