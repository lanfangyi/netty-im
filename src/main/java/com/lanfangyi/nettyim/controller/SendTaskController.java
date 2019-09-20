package com.lanfangyi.nettyim.controller;

import com.lanfangyi.nettyim.base.IMResponse;
import com.lanfangyi.nettyim.bean.SendTask;
import com.lanfangyi.nettyim.constants.ErrorCode;
import com.lanfangyi.nettyim.constants.SystemUserConstant;
import com.lanfangyi.nettyim.constants.UserTypeConstant;
import com.lanfangyi.nettyim.holder.ChannelHolder;
import com.lanfangyi.nettyim.listener.AsyncSendMsgListener;
import com.lanfangyi.nettyim.mapper.SendTaskMapper;
import com.lanfangyi.service.paramcheck.annotation.Valid;
import com.lanfangyi.service.paramcheck.annotation.activeannotation.NotBlank;
import io.netty.channel.Channel;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 所有发消息的动作都是一个推送任务，此controller专门处理推送任务
 *
 * @author lanfangyi@haodf.com
 * @version 1.0
 * @since 2019/8/28 6:24 PM
 */
@RestController
@RequestMapping("/sendTask")
public class SendTaskController {

    @Resource
    private SendTaskMapper sendTaskMapper;

    @Resource
    private AsyncSendMsgListener asyncSendMsgListener;

    /**
     * 创建推送任务
     *
     * @param providerId      消息发送者的ID
     * @param providerType    消息发送者的类型
     * @param receiveUserId   消息接受者的ID
     * @param receiveUserType 消息接收者的类型
     * @param dataJson        要推送的数据
     * @return 创建成功的ID
     */
    @PostMapping("/createSendTask")
    @Valid
    public IMResponse<Long> createSendTask(@Min(1) Long providerId, @NotBlank String providerType,
                                           @Min(1) Long receiveUserId, @NotBlank String receiveUserType, @RequestBody String dataJson) {

        //创建推送任务
        SendTask sendTask = new SendTask(providerId, providerType, receiveUserId, receiveUserType, dataJson);
        int insert = sendTaskMapper.insert(sendTask);
        if (insert == 1) {
            asyncSendMsgListener.send(sendTask.getId());
            return IMResponse.success(sendTask.getId());
        }
        return IMResponse.error(ErrorCode.DATABASE_ERROR, null);
    }

    /**
     * 给所有在线用户推送消息， 只有系统管理员有这个权限
     *
     * @param systemUserId       系统管理员的userId
     * @param systemUserPassword 系统管理员的密码
     * @param dataJson           发送的数据
     * @return 所创建的推送任务的ID集合
     */
    @PostMapping("/sendMsgToAllUser")
    @Valid
    public IMResponse<List<Long>> sendMsgToAllUser(@Min(1) Long systemUserId, @NotBlank String systemUserPassword, @RequestBody String dataJson) {
        if (systemUserId != SystemUserConstant.SYSTEM_USERID || !systemUserPassword.equals(SystemUserConstant.SYSTEM_USER_PASSWORD)) {
            return IMResponse.error(ErrorCode.PASSWORD_NOT_CURRECT, null);
        }
        List<Long> sendTaskIds = new ArrayList<>();
        //获取所有在线用户的信息
        ConcurrentHashMap<String, Channel> map = ChannelHolder.all();
        for (Map.Entry entry : map.entrySet()) {
            SendTask sendTask = new SendTask(SystemUserConstant.SYSTEM_USERID, SystemUserConstant.SYSTEM_USER_PASSWORD,
                (Long) entry.getKey(), UserTypeConstant.NOMAL_USER, dataJson);
            int insert = sendTaskMapper.insert(sendTask);
            if (insert == 1) {
                asyncSendMsgListener.send(sendTask.getId());
                sendTaskIds.add(sendTask.getId());
            }
        }
        return IMResponse.success(sendTaskIds);
    }

}
