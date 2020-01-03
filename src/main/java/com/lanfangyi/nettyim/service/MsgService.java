package com.lanfangyi.nettyim.service;

import com.lanfangyi.nettyim.bean.SendTask;

/**
 * @author lanfangyi@haodf.com
 * @version 1.0
 * @since 2019/12/31 2:16 PM
 */
public interface MsgService {

    public boolean sendMsg(SendTask sendTask) ;
}
