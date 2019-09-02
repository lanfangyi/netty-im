package com.lanfangyi.nettyim.future;

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

    private Long userId;
    private String name;
    private long sleepTime;

    private HttpStatus sendStatus;

    /**
     * 要发送的json数据
     */
    private String data;
    /**
     * 发送消息的channel集合
     */
    private Channel channel;

    public SendMsgFuture(Long id, String name) {
        this.userId = id;
        this.name = name;
    }

    public SendMsgFuture(Long id, Channel channel, String data) {
        this.userId = id;
        this.channel = channel;
        this.data = data;
    }

    /**
     * 推送消息的Future线程
     *
     * @return
     */
    @Override
    public SendMsgFutureResp call() {
        if (this.channel == null) {
            throw new SendMsgButChannelIsNullxception();
        }

        ChannelFuture channelFuture = channel.writeAndFlush(data);
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
