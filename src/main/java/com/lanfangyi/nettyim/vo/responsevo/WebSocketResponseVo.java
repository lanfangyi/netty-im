package com.lanfangyi.nettyim.vo.responsevo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 服务端给客户端发消息时，必须按照此结构进行封装数据
 *
 * @author lanfangyi@haodf.com
 * @version 1.0
 * @since 2020/1/17 3:42 PM
 */
@Getter
@Setter
@ToString
public class WebSocketResponseVo implements Serializable {

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
     * 业务类型。有的时候一个app内含有多个即时通讯场景。比如客服是一个，店铺交流又是一个，
     * 那这两种是不同的业务。用这个字段来区分消息是属于哪个业务的。
     */
    private String businessType;

    /**
     * 推送的消息
     */
    private String data;
}
