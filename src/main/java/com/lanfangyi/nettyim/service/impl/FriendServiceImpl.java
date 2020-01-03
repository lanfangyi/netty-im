package com.lanfangyi.nettyim.service.impl;

import com.lanfangyi.nettyim.bean.Friend;
import com.lanfangyi.nettyim.mapper.FriendMapper;
import com.lanfangyi.nettyim.service.FriendService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author lanfangyi@haodf.com
 * @version 1.0
 * @since 2019/12/31 6:30 PM
 */
@Service("friendService")
@Slf4j
public class FriendServiceImpl implements FriendService {

    @Resource
    private FriendMapper friendMapper;

    @Override
    public Long createFriend(long userId, String userType, long friendUserId, String friendUserType) {
        Friend friend = new Friend(userId, userType, friendUserId, friendUserType);
        int insert = friendMapper.insert(friend);

        return insert == 1 ? friend.getId() : 0L;
    }
}
