package com.lanfangyi.nettyim.service;

import com.lanfangyi.nettyim.bean.FriendChat;
import com.lanfangyi.nettyim.bean.Msg;

import java.util.List;

/**
 * @author lanfangyi@haodf.com
 * @version 1.0
 * @since 2020/1/7 9:53 AM
 */
public interface ChatMsgService {

    /**
     * 获取消息列表
     *
     * @param userId 用户的ID
     * @param userType 用户的类型
     * @return 消息列表数据
     */
    List<FriendChat> listFriendChat(long userId, String userType);

    /**
     * 分页获取与朋友的聊天消息
     * @param friendId
     * @param friendUserType
     * @param lastMsgId
     * @param pageSize
     * @return
     */
    List<Msg> listFriendMsg(long friendId, String friendUserType, int lastMsgId, int pageSize);
}
