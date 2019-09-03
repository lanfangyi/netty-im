package com.lanfangyi.nettyim.future;

import com.alibaba.druid.support.json.JSONUtils;
import com.lanfangyi.nettyim.bean.SendTask;
import com.lanfangyi.nettyim.exception.SendMsgButChannelIsNullxception;
import com.lanfangyi.nettyim.future.resp.SendMsgFutureResp;
import com.lanfangyi.nettyim.utils.DateUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import java.util.concurrent.Callable;

/**
 * @author lanfangyi@haodf.com
 * @version 1.0
 * @since 2019/9/2 5:57 PM
 */
@Data
@Slf4j
public class SendMsgFuture implements Callable<SendMsgFutureResp> {

    private long sleepTime;

    private HttpStatus sendStatus;

    /**
     * 要发送的json数据
     */
    private SendTask sendTask;

    /**
     * 发送消息的channel集合
     */
    private Channel channel;


    public SendMsgFuture(SendTask sendTask, Channel channel) {
        this.sendTask = sendTask;
        this.channel = channel;
    }

    /**
     * 推送消息的Future线程
     *
     * @return SendMsgFutureResp
     */
    @Override
    public SendMsgFutureResp call() {
        if (this.channel == null) {
            throw new SendMsgButChannelIsNullxception();
        }

        ChannelFuture channelFuture = channel.writeAndFlush(JSONUtils.toJSONString(this.sendTask));
        channelFuture.addListener((ChannelFutureListener) channelFuture2 -> log.info("push message success， time：", DateUtil.getNow()));

        channelFuture.addListener(future -> {
            if (future.isSuccess()) {
                //将sendTask的状态置完成
                sendStatus = HttpStatus.OK;
            } else {
                sendStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            }
        });
        return SendMsgFutureResp.getDefaultSendMsgFutureResp().setCode(sendStatus);
    }
}
