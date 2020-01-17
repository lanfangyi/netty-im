package com.lanfangyi.nettyim.mapper;

import com.lanfangyi.nettyim.bean.Friend;
import com.lanfangyi.nettyim.bean.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @author lanfangyi@haodf.com
 * @version 1.0
 * @since 2019/8/20 10:18 PM
 */
public interface FriendMapper {

    int insert(Friend friend);

    List<Friend> listFriend(@Param("userId") Long userId, @Param("userType") String userType);

    Long getFriendRelationShip(@Param("userId") Long userId,
                               @Param("userType") String userType,
                               @Param("friendUserId") Long friendUserId,
                               @Param("friendUserType") String friendUserType);
}
