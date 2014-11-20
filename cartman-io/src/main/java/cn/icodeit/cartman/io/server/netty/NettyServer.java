package cn.icodeit.cartman.io.server.netty;

import cn.icodeit.cartman.io.Server;
import cn.icodeit.cartman.io.context.ServerContext;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.util.SelfSignedCertificate;

import javax.net.ssl.SSLException;
import java.security.cert.CertificateException;

/**
 * @author zhoucong
 * @since 0.0.1
 */
public class NettyServer implements Server {
    @Override
    public void start(ServerContext context) throws CertificateException, SSLException {
        Runtime.getRuntime().addShutdownHook(new Thread(()-> System.out.println("shutdown")));
        System.out.println("start");

        // Configure SSL.
        final SslContext sslCtx;
        if (context.isSsl()) {
            SelfSignedCertificate ssc = new SelfSignedCertificate();
            sslCtx = SslContext.newServerContext(ssc.certificate(), ssc.privateKey());
        } else {
            sslCtx = null;
        }

        // Configure the server.
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.option(ChannelOption.SO_BACKLOG, 1024);
            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new NettyServerInitializer(sslCtx));

            Channel channel = bootstrap.bind(context.getPort()).syncUninterruptibly().channel();

            channel.closeFuture().syncUninterruptibly();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
