package com.lanfangyi.nettyim.service;

import com.lanfangyi.nettyim.bean.SendTask;

/**
 * @author lanfangyi@haodf.com
 * @version 1.0
 * @since 2019/12/31 2:16 PM
 */
public interface MsgService {

    /**
     * 推送消息
     *
     * @param sendTask 推送任务
     * @return 成功返回true，失败返回false
     */
    boolean sendMsg(SendTask sendTask);
}
