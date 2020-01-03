package com.lanfangyi.nettyim.service.impl;

import com.alibaba.druid.support.json.JSONUtils;
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
                log.error("通道不可用，无法推送消息. sendTask：{}", JSONUtils.toJSONString(sendTask));
                return false;
            }

            //给尝试次数加一
            sendTask.increaseTryTimes();
            //创建发送线程
            SendMsgFuture sendMsgFuture = new SendMsgFuture(sendTask, channel);
            //进入线程队列, 执行发送任务
            ListenableFuture<SendMsgFutureResp> future = FutureTaskPool.getExecutorService().submit(sendMsgFuture);

            Futures.addCallback(future, new FutureCallback<SendMsgFutureResp>() {
                @Override
                public void onSuccess(SendMsgFutureResp sendMsgFutureResp) {
                    log.info("Send message success. time:{}", DateUtil.getNow());
                }

                @Override
                public void onFailure(Throwable throwable) {
                    // TODO: 2019/9/20  加入重试线程池
                    log.error("Send message fail. time:{}, exception:{}", DateUtil.getNow(), JSONUtils.toJSONString(throwable.getCause()));
                }
            });
            return true;
        }
        return false;
    }
}
