package com.lanfangyi.nettyim.handler;

import com.lanfangyi.nettyim.utils.DateUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @author lanfangyi@haodf.com
 * @version 1.0
 * @since 2019/7/31 9:27 PM
 */
public class ChatHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    // 用于记录和管理所有客户端的channle
    public static ChannelGroup users = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("连接建立，时间：" + DateUtil.getNow());
        users.add(ctx.channel());
//        super.handlerAdded(ctx);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("连接断开，时间：" + DateUtil.getNow());
        System.out.println(ctx.channel().id().asLongText());
        System.out.println(ctx.channel().id().asShortText());
//        users.remove(ctx.channel());
//        super.handlerRemoved(ctx);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        //获取客户端传过来的消息
        String content = msg.text();
        System.out.println("接收到的数据:" + content);

        //转发给所有的客户端
        users.writeAndFlush(
            new TextWebSocketFrame("服务器在"+DateUtil.getNow()+"收到消息："+content)
        );
    }
}
