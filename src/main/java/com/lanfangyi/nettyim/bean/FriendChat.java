package com.lanfangyi.nettyim.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 消息列表所对应的实体
 *
 * @author lanfangyi@haodf.com
 * @version 1.0
 * @since 2020/1/7 9:45 AM
 */
@Getter
@Setter
@ToString
public class FriendChat extends BaseBean {

    /**
     * 用户的ID
     */
    private Long userId;

    /**
     * 用户的类型
     */
    private String userType;

    /**
     * 朋友的ID
     */
    private Long friendUserId;

    /**
     * 朋友的类型
     */
    private String friendUserType;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 最后一条消息的内容
     */
    private String latestMsg;
}
