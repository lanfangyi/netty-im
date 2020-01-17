package com.lanfangyi.nettyim.controller;

import com.google.common.collect.Lists;
import com.lanfangyi.nettyim.base.IMResponse;
import com.lanfangyi.nettyim.bean.Friend;
import com.lanfangyi.nettyim.bean.User;
import com.lanfangyi.nettyim.constants.ErrorCode;
import com.lanfangyi.nettyim.constants.StatusConstant;
import com.lanfangyi.nettyim.constants.UserTypeConstant;
import com.lanfangyi.nettyim.mapper.FriendMapper;
import com.lanfangyi.nettyim.mapper.UserMapper;
import com.lanfangyi.nettyim.service.FriendService;
import com.lanfangyi.nettyim.service.UserService;
import com.lanfangyi.nettyim.utils.IdGetUtil;
import com.lanfangyi.nettyim.vo.responsevo.FriendVo;
import com.lanfangyi.service.paramcheck.annotation.Valid;
import com.lanfangyi.service.paramcheck.annotation.activeannotation.Min;
import com.lanfangyi.service.paramcheck.annotation.activeannotation.NotBlank;
import com.lanfangyi.service.paramcheck.annotation.activeannotation.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.lanfangyi.nettyim.constants.ErrorCode.FRIEND_HAS_EXIST;
import static com.lanfangyi.nettyim.constants.ErrorCode.USER_NOT_EXIST;

/**
 * @author lanfangyi@haodf.com
 * @version 1.0
 * @since 2019/8/20 10:44 PM
 */
@RestController
@RequestMapping("/friend")
@Slf4j
public class FriendController {

    @Resource
    private FriendService friendService;

    @Resource
    private UserService userService;

    /**
     * 添加好友
     *
     * @param userId         自己的ID
     * @param userType       自己的类型
     * @param friendUserId   好友的ID
     * @param friendUserType 好友类型
     * @return Friend 好友的信息
     */
    @PostMapping("/addFriend")
    @Valid
    public IMResponse<Long> addFriend(
        @NotNull Long userId,
        @NotBlank(among = {UserTypeConstant.NORMAL_USER, UserTypeConstant.SYSTEM_USER}) String userType,
        @NotNull Long friendUserId,
        @NotBlank(among = {UserTypeConstant.NORMAL_USER, UserTypeConstant.SYSTEM_USER}) String friendUserType
    ) {

        //先查询用户是否存在
        List<User> users = userService.listUser(Lists.newArrayList(userId, friendUserId));
        if (users.size() != 2) {
            return IMResponse.error(USER_NOT_EXIST);
        }

        Long friendId = friendService.createFriend(userId, userType, friendUserId, friendUserType);
        if (friendId > 0) {
            return IMResponse.success(friendId);
        } else if (friendId == -1) {
            return IMResponse.error(FRIEND_HAS_EXIST);
        }
        return IMResponse.error(ErrorCode.DATABASE_ERROR);
    }

    /**
     * 拉取好友列表，一次性返回所有好友数据
     *
     * @param userId   用户的ID
     * @param userType 用户的类型
     * @return 好友列表
     */
    @PostMapping("/listFriend")
    @Valid
    public IMResponse<List<FriendVo>> listFriend(@Min(1) Long userId,
                                                 @NotBlank(among = {UserTypeConstant.NORMAL_USER}) String userType) {
        List<Friend> friends = friendService.listFriend(userId, userType);
        List<FriendVo> friendVoList = friends.stream().map(FriendVo::new).collect(Collectors.toList());
        //查出头像
        List<Long> friendUserIds = friends.stream().map(Friend::getUserId).collect(Collectors.toList());
        List<User> friendUsers = userService.listUser(friendUserIds);
        //转成map
        Map<Long, User> userMap = new HashMap<>(10);
        for (User friendUser : friendUsers) {
            userMap.put(friendUser.getId(), friendUser);
        }

        for (FriendVo friendVo : friendVoList) {
            friendVo.setHeadImg(userMap.get(friendVo.getFriendUserId()).getHeadImg());
            friendVo.setFriendUserName(userMap.get(friendVo.getFriendUserId()).getName());
        }
        return IMResponse.success(friendVoList);
    }

}
