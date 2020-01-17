package com.lanfangyi.nettyim.service;

import com.lanfangyi.nettyim.bean.Friend;
import com.lanfangyi.nettyim.vo.responsevo.FriendVo;

import java.util.List;

/**
 * @author lanfangyi@haodf.com
 * @version 1.0
 * @since 2019/12/31 4:58 PM
 */
public interface FriendService {

    /**
     * 添加好友关系
     *
     * @param userId         用户ID
     * @param userType       用户的类型
     * @param friendUserId   好友的ID
     * @param friendUserType 好友类型
     * @return 好友关系的ID
     */
    Long createFriend(long userId, String userType, long friendUserId, String friendUserType);

    /**
     * 获取所有好友数据
     *
     * @param userId   用户ID
     * @param userType 用户类型
     * @return 好友数据
     */
    List<Friend> listFriend(long userId, String userType);

}
