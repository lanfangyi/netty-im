package com.lanfangyi.nettyim.bean;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author lanfangyi@haodf.com
 * @version 1.0
 * @since 2019/9/2 5:13 PM
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Msg extends BaseBean {

    private Long providerId;

    private String providerType;

    private Long receiveUserId;

    private String receiveUserType;

    /**
     * 1代表不需要call， 2代表需要
     */
    private Integer isCall;

    /**
     * 消息类型
     */
    private Integer type;

    private String data;

    /**
     * 状态：已发送，未发送，已读
     */
    private Integer status;

}
