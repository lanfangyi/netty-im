package com.lanfangyi.nettyim.service;

/**
 * @author lanfangyi@haodf.com
 * @version 1.0
 * @since 2019/12/31 4:58 PM
 */
public interface FriendService {

    Long createFriend(long userId, String userType, long friendUserId, String friendUserType);
}
