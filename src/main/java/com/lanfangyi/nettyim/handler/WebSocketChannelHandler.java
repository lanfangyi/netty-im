package com.lanfangyi.nettyim.handler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * @author lanfangyi@haodf.com
 * @version 1.0
 * @since 2019/7/30 2:25 PM
 */
public class WebSocketChannelHandler extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel e) throws Exception {
        e.pipeline().addLast("http-codec",new HttpServerCodec());
        e.pipeline().addLast("aggregator",new HttpObjectAggregator(65536));
        e.pipeline().addLast("http-chunked",new ChunkedWriteHandler());
        e.pipeline().addLast("handler",new WebSocketHandler());
    }
}