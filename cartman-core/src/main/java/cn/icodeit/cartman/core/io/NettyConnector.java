package cn.icodeit.cartman.core.io;

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

import static cn.icodeit.cartman.core.io.server.ServerContext.port;
import static cn.icodeit.cartman.core.io.server.ServerContext.ssl;

/**
 * @author zhoucong
 */
public class NettyConnector implements LifeCycle {

    private volatile boolean initialized = false;
    private Thread serverThread;

    @Override
    public void start() {
        if (!initialized) {
            initialized = true;

            serverThread = new Thread(() -> {
                try {
                    doStart();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            serverThread.start();
        }
    }

    @Override
    public void stop() {
        serverThread.interrupt();
    }

    private void doStart() {
        // Configure SSL.
        SslContext sslCtx;
        try {
            if (ssl()) {
                SelfSignedCertificate ssc = new SelfSignedCertificate();
                sslCtx = SslContext.newServerContext(ssc.certificate(), ssc.privateKey());
            } else {
                sslCtx = null;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
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
                    .childHandler(new NettyInitializer(sslCtx));

            try {
                Channel channel = bootstrap.bind(port()).sync().channel();
                channel.closeFuture().sync();
            } catch (InterruptedException e) {
                System.out.println("Server shutdown");
            }
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
