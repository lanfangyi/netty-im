package com.lanfangyi.nettyim.vo.requestvo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 客户端给服务端发消息时，必须按照此结构进行封装数据
 *
 * @author lanfangyi@haodf.com
 * @version 1.0
 * @since 2020/1/17 3:41 PM
 */
@Getter
@Setter
@ToString
public class WebSocketRequestVo implements Serializable {

    /**
     * 消息的生产者ID
     */
    private Long providerId;

    /**
     * 消息生产者的类型
     */
    private String providerType;

    /**
     * 消息接收者的ID
     */
    private Long receiveUserId;

    /**
     * 消息接收者的类型
     */
    private String receiveUserType;

    /**
     * 推送的消息
     */
    private String data;
}
