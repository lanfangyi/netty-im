package com.lanfangyi.nettyim.mapper;

import com.lanfangyi.nettyim.bean.FriendChat;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lanfangyi@haodf.com
 * @version 1.0
 * @since 2020/1/7 9:56 AM
 */
public interface FriendChatMapper {

    /**
     * 插入聊天数据
     *
     * @param friendChat 消息数据
     * @return 成功返回1，失败返回0
     */
    int insert(FriendChat friendChat);

    /**
     * 当有信息消息的时候，更新最新消息
     *
     * @param friendChat 消息数据
     * @return 成功返回1，失败返回0
     */
    int update(FriendChat friendChat);

    /**
     * 获取消息列表
     * @param userId 用户ID
     * @param userType 用户类型
     * @return 消息列表数据
     */
    List<FriendChat> listFriendChat(@Param("userId") long userId, @Param("userType") String userType);
}
