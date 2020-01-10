package com.lanfangyi.nettyim.controller;

import com.google.common.collect.Lists;
import com.lanfangyi.nettyim.base.IMResponse;
import com.lanfangyi.nettyim.bean.FriendChat;
import com.lanfangyi.nettyim.bean.User;
import com.lanfangyi.nettyim.constants.UserTypeConstant;
import com.lanfangyi.nettyim.service.ChatMsgService;
import com.lanfangyi.nettyim.vo.responsevo.FriendChatVo;
import com.lanfangyi.service.paramcheck.annotation.activeannotation.Min;
import com.lanfangyi.service.paramcheck.annotation.activeannotation.NotBlank;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 消息列表对应的controller
 *
 * @author lanfangyi@haodf.com
 * @version 1.0
 * @since 2020/1/6 8:55 PM
 */
@RestController
@RequestMapping("/chatMsg")
@Slf4j
public class ChatMsgController {

    @Resource
    private ChatMsgService chatMsgService;

    /**
     * 获取用户的聊天列表
     *
     * @param userId   用户的ID
     * @param userType 用户的类型
     * @return
     */
    @GetMapping("/listFriendChat")
    public IMResponse<List<FriendChatVo>> listFriendChat(@Min(1) Long userId,
                                                         @NotBlank(among = UserTypeConstant.NORMAL_USER) String userType) {

//        FriendChatVo friendChatVo = new FriendChatVo();
//        friendChatVo.setId(1L);
//        friendChatVo.setFriendUserName("ceshi");
//        friendChatVo.setHeadImgUrl(User.DEFAULT_HEAD_IMG);
//        friendChatVo.setLatestMsg("测试的数据");
//        friendChatVo.setTime(new Date().toString());
//        System.out.println(friendChatVo);
//        return IMResponse.success(Lists.newArrayList(friendChatVo));

        List<FriendChat> friendChats = chatMsgService.listFriendChat(userId, userType);

        List<FriendChatVo> friendChatVoList = friendChats.stream().map(
            a -> new FriendChatVo(a.getId(), a.getLatestMsg(), User.DEFAULT_HEAD_IMG, a.getUTime().toString()))
            .collect(Collectors.toList());
        return IMResponse.success(friendChatVoList);
    }
}
