package com.lanfangyi.nettyim.service.impl;

import com.lanfangyi.nettyim.base.IMResponse;
import com.lanfangyi.nettyim.bean.SendTask;
import com.lanfangyi.nettyim.listener.AsyncSendMsgListener;
import com.lanfangyi.nettyim.mapper.SendTaskMapper;
import com.lanfangyi.nettyim.service.SendTaskService;
import com.lanfangyi.nettyim.utils.IdGetUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author lanfangyi@haodf.com
 * @version 1.0
 * @since 2019/12/31 4:14 PM
 */
@Service("sendTaskService")
@Slf4j
public class SendTaskServiceImpl implements SendTaskService {

    @Resource
    private SendTaskMapper sendTaskMapper;

    @Resource
    private AsyncSendMsgListener asyncSendMsgListener;

    /**
     * 创建发送任务
     *
     * @param providerId      发送这的ID
     * @param providerType    发送者类型
     * @param receiveUserId   接收者ID
     * @param receiveUserType 接收者类型
     * @param dataJson        要发送的内容
     * @return 任务的ID
     */
    @Override
    public Long createSendTask(Long providerId,
                               String providerType,
                               Long receiveUserId,
                               String receiveUserType,
                               String dataJson) {
        SendTask sendTask = new SendTask(providerId, providerType, receiveUserId, receiveUserType, dataJson);
        sendTask.setId(IdGetUtil.get());
        int insert = sendTaskMapper.insert(sendTask);
        if (insert == 1) {
            asyncSendMsgListener.send(sendTask.getId());
            return sendTask.getId();
        }
        return 0L;
    }
}
