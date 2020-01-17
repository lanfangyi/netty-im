package com.lanfangyi.nettyim.service;

/**
 * @author lanfangyi@haodf.com
 * @version 1.0
 * @since 2019/12/31 4:14 PM
 */
public interface SendTaskService {

    /**
     * 创建消息推送任务
     *
     * @param providerId      消息提供者ID
     * @param providerType    消息提供者类型
     * @param receiveUserId   消息接受者ID
     * @param receiveUserType 消息接受者类型
     * @param dataJson        要推送的数据
     * @return 消息的ID
     */
    public Long createSendTask(Long providerId, String providerType, Long receiveUserId, String receiveUserType, String dataJson);
}
