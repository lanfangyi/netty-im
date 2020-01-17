package com.lanfangyi.nettyim.handler;

import com.lanfangyi.nettyim.config.NettyConfig;
import com.lanfangyi.nettyim.holder.ChannelHolder;
import com.lanfangyi.nettyim.utils.DateUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

/**
 * @author lanfangyi@haodf.com
 * @version 1.0
 * @since 2019/7/30 2:22 PM
 */
@Slf4j
public class WebSocketHandler extends SimpleChannelInboundHandler<Object> {

    private WebSocketServerHandshaker webSocketServerHandshaker;
    private static final String WEB_SOCKET_URL = "ws://127.0.0.1:8081/ws";

    /**
     * 客户端与服务端创建链接的时候调用
     *
     * @param context 上下文
     */
    @Override
    public void channelActive(ChannelHandlerContext context) {
        NettyConfig.group.add(context.channel());
        log.info("客户端建立连接，时间：{}", DateUtil.getNow());
        log.info("channelid:" + context.channel().id().asLongText());
        //将channel存入
        ChannelHolder.putChannelNoUserId(context.channel().id().toString(), context.channel());
    }


    /**
     * 客户端与服务端断开连接的时候调用
     *
     * @param context 上下文
     */
    @Override
    public void channelInactive(ChannelHandlerContext context) {
        NettyConfig.group.remove(context.channel());
        log.info("客户端与服务端连接断开，时间：{}", DateUtil.getNow());
        ChannelHolder.removeChannelNoUserId(context.channel().id().toString());
        log.info("清除channel信息成功");
    }

    /**
     * 服务端接收客户端发送过来的数据结束之后调用
     *
     * @param context 上下文
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext context) {
        log.info("服务器接读取完客户端的消息");
        context.flush();
    }

    /**
     * 出现异常的时候调用
     *
     * @param context   上下文
     * @param throwable 错误
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext context, Throwable throwable) {
        log.info("捕捉到异常");
        throwable.printStackTrace();
        context.close();
    }

    /**
     * 服务端处理客户端websocket请求的核心方法
     *
     * @param channelHandlerContext 上下文
     * @param o                     处理的数据
     */
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) {
        log.info("服务端处理客户端websocke请求的核心方法");
        //处理客户端向服务端发起的http握手请求
        if (o instanceof FullHttpRequest) {
            handHttpRequest(channelHandlerContext, (FullHttpRequest) o);
        } else if (o instanceof WebSocketFrame) {
            //处理websocket链接业务
            handWebSocketFrame(channelHandlerContext, (WebSocketFrame) o);
        }
    }

    /**
     * 处理客户端与服务端之间的websocket业务
     *
     * @param context        上下文
     * @param webSocketFrame 消息封装体
     */
    private void handWebSocketFrame(ChannelHandlerContext context, WebSocketFrame webSocketFrame) {
        if (webSocketFrame instanceof CloseWebSocketFrame) {
            //判断是否是关闭websocket的指令
            webSocketServerHandshaker.close(context.channel(), (CloseWebSocketFrame) webSocketFrame.retain());
        }
        if (webSocketFrame instanceof PingWebSocketFrame) {
            //判断是否是ping消息
            context.channel().write(new PongWebSocketFrame(webSocketFrame.content().retain()));
            return;
        }
        if (!(webSocketFrame instanceof TextWebSocketFrame)) {
            //判断是否是文本消息
            log.info("不支持文本消息");
            throw new RuntimeException(this.getClass().getName());
        }
        //返回应答消息
        //获取客户端向服务端发送的消息
        String content = ((TextWebSocketFrame) webSocketFrame).text();
        log.info("服务端收到客户端的消息：" + content);
        if (!StringUtils.isEmpty(content) && content.contains("_normal_user")) {
            //将用户ID和channel进行绑定
            log.info("将用户ID和channel进行绑定");
            ChannelHolder.putAndOverWrite(content, context.channel());
        }
        TextWebSocketFrame textWebSocketFrame = new TextWebSocketFrame(context.channel().id() + ":" + content);
        //服务端向每个连接上来的客户端发送消息
        NettyConfig.group.writeAndFlush(textWebSocketFrame);
        textWebSocketFrame = new TextWebSocketFrame("lanfangyi测试");
        NettyConfig.group.writeAndFlush(textWebSocketFrame);
    }


    /**
     * 处理客户端向服务端发起http握手请求业务
     *
     * @param context         上下文
     * @param fullHttpRequest 请求的所有信息
     */
    private void handHttpRequest(ChannelHandlerContext context, FullHttpRequest fullHttpRequest) {
        //判断是否http握手请求
        if (!fullHttpRequest.decoderResult().isSuccess() || !("websocket".equals(fullHttpRequest.headers().get("Upgrade")))) {
            sendHttpResponse(context, fullHttpRequest, new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST));
            return;
        }
        WebSocketServerHandshakerFactory webSocketServerHandshakerFactory = new WebSocketServerHandshakerFactory(WEB_SOCKET_URL, "websocket", false);
        webSocketServerHandshaker = webSocketServerHandshakerFactory.newHandshaker(fullHttpRequest);
        if (webSocketServerHandshaker == null) {
            WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(context.channel());
        } else {
            webSocketServerHandshaker.handshake(context.channel(), fullHttpRequest);
        }
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof WebSocketServerProtocolHandler.HandshakeComplete) {
            //完成握手
            log.info("握手完成");
        } else {
            super.userEventTriggered(ctx, evt);
        }
    }

    /**
     * 服务端向客户端发送响应消息，作用是告知客户端，服务端已经收到消息
     *
     * @param context                 上下文
     * @param fullHttpRequest         请求的所有信息
     * @param defaultFullHttpResponse 默认的回复
     */
    private void sendHttpResponse(ChannelHandlerContext context, FullHttpRequest fullHttpRequest, DefaultFullHttpResponse defaultFullHttpResponse) {
        if (defaultFullHttpResponse.status().code() != 200) {
            ByteBuf buf = Unpooled.copiedBuffer(defaultFullHttpResponse.status().toString(), CharsetUtil.UTF_8);
            defaultFullHttpResponse.content().writeBytes(buf);
            buf.release();
        }
        //服务端向客户端发送数据
        ChannelFuture future = context.channel().writeAndFlush(defaultFullHttpResponse);
        if (defaultFullHttpResponse.status().code() != 200) {
            future.addListener(ChannelFutureListener.CLOSE);
        }
    }

}

