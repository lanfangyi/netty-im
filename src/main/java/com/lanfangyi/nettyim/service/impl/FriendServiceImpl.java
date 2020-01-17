package com.lanfangyi.nettyim.service.impl;

import com.lanfangyi.nettyim.bean.Friend;
import com.lanfangyi.nettyim.mapper.FriendMapper;
import com.lanfangyi.nettyim.service.FriendService;
import com.lanfangyi.nettyim.vo.responsevo.FriendVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

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
    @Transactional(rollbackFor = Throwable.class)
    public Long createFriend(long userId, String userType, long friendUserId, String friendUserType) {

        //查询是否存在好友关系了
        Long friendRelationShip = friendMapper.getFriendRelationShip(userId, userType, friendUserId, friendUserType);
        if (friendRelationShip != null && friendRelationShip > 0) {
            return -1L;
        }

        Friend friend = new Friend(userId, userType, friendUserId, friendUserType);
        int insert = friendMapper.insert(friend);

        //朋友关系是双向的
        friend = new Friend(friendUserId, friendUserType, userId, userType);
        friendMapper.insert(friend);
        return insert == 1 ? friend.getId() : 0L;
    }

    @Override
    public List<Friend> listFriend(long userId, String userType) {
        return friendMapper.listFriend(userId, userType);
    }
}
