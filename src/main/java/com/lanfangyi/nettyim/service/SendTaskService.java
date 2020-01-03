package com.lanfangyi.nettyim.service;

/**
 * @author lanfangyi@haodf.com
 * @version 1.0
 * @since 2019/12/31 4:14 PM
 */
public interface SendTaskService {

    public Long createSendTask(Long providerId, String providerType, Long receiveUserId, String receiveUserType, String dataJson);
}
