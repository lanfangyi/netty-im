package com.lanfangyi.nettyim.listener;

import com.lanfangyi.nettyim.bean.SendTask;
import com.lanfangyi.nettyim.constants.StatusConstant;
import com.lanfangyi.nettyim.holder.ChannelHolder;
import com.lanfangyi.nettyim.mapper.SendTaskMapper;
import com.lanfangyi.nettyim.service.SendMsgService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * @author lanfangyi@haodf.com
 * @version 1.0
 * @since 2019/9/2 5:39 PM
 */
@Component
@EnableAsync
@Slf4j
public class AsyncSendMsgListener {

    @Resource
    private SendTaskMapper sendTaskMapper;

    @Resource
    private SendMsgService sendMsgService;

    @EventListener
    @Async
    public void send(Long sendTaskId) {
        SendTask sendTask = sendTaskMapper.findById(sendTaskId);
        if (sendTask == null) {
            return;
        }
        //当前就在线，当即发送
        //判断用户是否在线
        if (judgeLogin(sendTask.getUserKey())) {
            String data = sendTask.getData();
            //推送
            if (!StringUtils.isEmpty(data)) {
                boolean b = sendMsgService.sendMsg(sendTask);
                if (b) {
                    sendTaskMapper.updateStatus(sendTaskId, StatusConstant.INVALID);
                }
            }
        }
    }

    /**
     * 判断用户的在线状态
     * @param userKey 用户ID和用户类型组成的字符串，用户的标示
     */
    private boolean judgeLogin(String userKey) {
        //判断本机
        return ChannelHolder.getChannel(userKey) == null;
    }
}
