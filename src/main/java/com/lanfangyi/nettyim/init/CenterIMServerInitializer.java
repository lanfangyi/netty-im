package com.lanfangyi.nettyim.init;

import com.lanfangyi.nettyim.handler.WebSocketHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;


/**
 * @author lanfangyi@haodf.com
 * @version 1.0
 * @since 2019/8/2 10:38 PM
 */
public class CenterIMServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) {
        //获取pipeline管道
        ChannelPipeline pipeline = socketChannel.pipeline();

        // ====================== 支持http协议   开始 ======================
        //配置http编解码器，因为WebSocket是基于http协议的应用层协议
        pipeline.addLast(new HttpServerCodec());

        //对写大数据流的支持
        pipeline.addLast(new ChunkedWriteHandler());

        // 对httpMessage进行聚合，聚合成FullHttpRequest或FullHttpResponse 至于聚合成哪个，由请求或者响应来决定
        // 几乎在netty中的编程，都会使用到此handler
        pipeline.addLast(new HttpObjectAggregator(1024*64));
        // ====================== 支持http协议   结束 ======================



        // ====================== 增加心跳支持 start  TODO  ======================
        //参考文章：http://www.52im.net/thread-2697-1-1.html
        // 针对客户端，如果在1分钟时没有向服务端发送读写心跳(ALL)，则主动断开
        // 如果是读空闲或者写空闲，不处理
        //pipeline.addLast(new IdleStateHandler(8, 10, 12));
        // 自定义的空闲状态检测
        //pipeline.addLast(new HeartBeatHandler());
        // ====================== 增加心跳支持 end    ======================


        // ====================== 支持WebSocket协议   开始 ======================
        /**
         * websocket 服务器处理的协议，用于指定给客户端连接访问的路由 : /ws
         * 本handler会帮你处理一些繁重的复杂的事
         * 会帮你处理握手动作： handshaking（close, ping, pong） ping + pong = 心跳
         * 对于websocket来讲，都是以frames进行传输的，不同的数据类型对应的frames也不同
         */
        pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));

        // 自定义的handler
        pipeline.addLast(new WebSocketHandler());

        // ====================== 支持WebSocket协议   结束 ======================

    }
}
