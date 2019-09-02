package com.lanfangyi.nettyim.controller;

import com.lanfangyi.nettyim.base.IMResponse;
import com.lanfangyi.nettyim.bean.SendTask;
import com.lanfangyi.nettyim.constants.ErrorCode;
import com.lanfangyi.nettyim.mapper.SendTaskMapper;
import com.lanfangyi.service.paramcheck.annotation.activeannotation.NotBlank;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.Min;

/**
 * @author lanfangyi@haodf.com
 * @version 1.0
 * @since 2019/8/28 6:24 PM
 */
@RestController
@RequestMapping("/sendTask")
public class SendTaskController {

    @Resource
    private SendTaskMapper sendTaskMapper;

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
    public IMResponse<Long> createSendTask(@Min(1) Long providerId, @NotBlank String providerType,
                                           @Min(1) Long receiveUserId, @NotBlank String receiveUserType, @RequestBody String dataJson) {

        //创建推送任务
        SendTask sendTask = new SendTask(providerId, providerType, receiveUserId, receiveUserType, dataJson);
        int insert = sendTaskMapper.insert(sendTask);
        if (insert == 1) {
            return IMResponse.success(sendTask.getId());
        }
        return IMResponse.error(ErrorCode.DATABASE_ERROR, null);
    }
}
