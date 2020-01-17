package com.lanfangyi.nettyim.service.impl;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.lanfangyi.nettyim.bean.SendTask;
import com.lanfangyi.nettyim.future.SendMsgFuture;
import com.lanfangyi.nettyim.future.resp.SendMsgFutureResp;
import com.lanfangyi.nettyim.holder.ChannelHolder;
import com.lanfangyi.nettyim.pool.FutureTaskPool;
import com.lanfangyi.nettyim.service.MsgService;
import com.lanfangyi.nettyim.utils.DateUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author lanfangyi@haodf.com
 * @version 1.0
 * @since 2019/9/2 5:44 PM
 */
@Service("msgService")
@Slf4j
public class MsgServiceImpl implements MsgService {

    @Override
    public boolean sendMsg(SendTask sendTask) {
        if (null == sendTask) {
            return false;
        }
        if (!StringUtils.isEmpty(sendTask.getData())) {
            //拿到所有路由信息
            Channel channel = ChannelHolder.getChannel(sendTask.getUserKey());

            if (channel == null || !channel.isActive()) {
                log.warn("通道不可用，无法推送消息. sendTask：{}", JSON.toJSON(sendTask));
                return false;
            }

            //给尝试次数加一
            sendTask.increaseTryTimes();
            //推送的消息必须用WebSocketFrame进行封装，否则推送不成功。
            ChannelFuture channelFuture = channel.writeAndFlush(new TextWebSocketFrame(sendTask.getData()));
            // TODO: 2020/1/17 添加发送监听器，确保消息推送成功
            return true;
        }
        return false;
    }
}
