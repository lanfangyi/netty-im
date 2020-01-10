package com.lanfangyi.nettyim.controller;

import com.lanfangyi.nettyim.base.IMResponse;
import com.lanfangyi.nettyim.bean.Friend;
import com.lanfangyi.nettyim.bean.User;
import com.lanfangyi.nettyim.constants.ErrorCode;
import com.lanfangyi.nettyim.constants.StatusConstant;
import com.lanfangyi.nettyim.constants.UserTypeConstant;
import com.lanfangyi.nettyim.mapper.FriendMapper;
import com.lanfangyi.nettyim.mapper.UserMapper;
import com.lanfangyi.nettyim.service.FriendService;
import com.lanfangyi.nettyim.utils.IdGetUtil;
import com.lanfangyi.service.paramcheck.annotation.Valid;
import com.lanfangyi.service.paramcheck.annotation.activeannotation.NotBlank;
import com.lanfangyi.service.paramcheck.annotation.activeannotation.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

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
        Long friendId = friendService.createFriend(userId, userType, friendUserId, friendUserType);
        if (friendId > 0) {
            return IMResponse.success(friendId);
        }
        return IMResponse.error(ErrorCode.DATABASE_ERROR);
    }

}
