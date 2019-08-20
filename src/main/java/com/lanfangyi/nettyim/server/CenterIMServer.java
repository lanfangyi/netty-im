package com.lanfangyi.nettyim.server;

import com.lanfangyi.nettyim.init.CenterIMServerInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.net.InetSocketAddress;

@Component
public class CenterIMServer {

    private final static Logger LOGGER = LoggerFactory.getLogger(CenterIMServer.class);

    /**
     * 创建两个线程池 boss线程池中的线程是client，主要负责端口的监听；work线程池中的线程主要负责读写任务，即消息的收发；
     */
    private EventLoopGroup boss = new NioEventLoopGroup();
    private EventLoopGroup work = new NioEventLoopGroup();

    @Value("${cim.server.port:8081}")
    private int nettyPort;

    /**
     * 启动netty服务端
     */
    @PostConstruct
    public void startNettyServer() throws InterruptedException {
        //创建服务实例
        ServerBootstrap bootstrap = new ServerBootstrap()
            //设置工作线程组
            .group(boss, work)
            //设置channel类型
            .channel(NioServerSocketChannel.class)
            //设置服务器端口
            .localAddress(new InetSocketAddress(nettyPort))
            //保持长连接
            .childOption(ChannelOption.SO_KEEPALIVE, true)
            //设置channel处理器
            .childHandler(new CenterIMServerInitializer());

        //异步启动服务器，future的结果就是服务器启动结果
        ChannelFuture future = bootstrap.bind().sync();
        if (future.isSuccess()) {
            LOGGER.info("启动 CenterIM server 成功");
        }
    }

    /**
     * 当守护线程退出的时候销毁
     */
    @PreDestroy
    public void destroy() {
        //安全的销毁两个工作线程池
        boss.shutdownGracefully().syncUninterruptibly();
        work.shutdownGracefully().syncUninterruptibly();
        LOGGER.info("关闭 CenterIM server 成功");
    }

}
