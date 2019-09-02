package com.lanfangyi.nettyim.service;

import com.lanfangyi.nettyim.bean.SendTask;
import com.lanfangyi.nettyim.future.SendMsgFuture;
import com.lanfangyi.nettyim.future.resp.SendMsgFutureResp;
import com.lanfangyi.nettyim.holder.ChannelHolder;
import com.lanfangyi.nettyim.pool.FutureTaskPool;
import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author lanfangyi@haodf.com
 * @version 1.0
 * @since 2019/9/2 5:44 PM
 */
@Service
@Slf4j
public class SendMsgService {

    public boolean sendMsg(SendTask sendTask) {
        if (null == sendTask) {
            return false;
        }
        if (!StringUtils.isEmpty(sendTask.getData())) {
            //拿到所有路由信息
            Channel channel = ChannelHolder.getChannel(sendTask.getReceiveUserId());
            //创建发送线程
            SendMsgFuture sendMsgFuture = new SendMsgFuture(sendTask.getReceiveUserId(), channel, sendTask.getData());
            //进入线程队列, 执行发送任务
            Future<SendMsgFutureResp> sendFuture = FutureTaskPool.getExecutorService().submit(sendMsgFuture);

            try {
                SendMsgFutureResp sendMsgFutureResp = sendFuture.get();
            } catch (InterruptedException | ExecutionException e) {
                log.error("send msg occur error");
                // TODO: 2019/9/2
                //加入监听器，获取返回结果，发送失败的，进入重试线程池
                return false;
            }
            // TODO: 2019/9/2
            //加入监听器，获取返回结果，发送失败的，进入重试线程池
            return sendMsgFuture.getSendStatus() == HttpStatus.OK;
        }
        return false;
    }
}
