package com.lanfangyi.nettyim.service.impl;

import com.lanfangyi.nettyim.bean.FriendChat;
import com.lanfangyi.nettyim.bean.Msg;
import com.lanfangyi.nettyim.mapper.FriendChatMapper;
import com.lanfangyi.nettyim.service.ChatMsgService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lanfangyi@haodf.com
 * @version 1.0
 * @since 2020/1/7 9:55 AM
 */
@Service("chatMsgService")
public class ChatMsgServiceImpl implements ChatMsgService {

    @Resource
    private FriendChatMapper friendChatMapper;

    /**
     * 获取用户的消息列表
     *
     * @param userId   用户ID
     * @param userType 用户类型
     * @return 消息列表数据
     */
    @Override
    public List<FriendChat> listFriendChat(long userId, String userType) {
        return friendChatMapper.listFriendChat(userId, userType);
    }

    @Override
    public List<Msg> listFriendMsg(long friendId, String friendUserType, int pageId, int pageSize) {
        return null;
    }
}
