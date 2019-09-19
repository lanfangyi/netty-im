package com.lanfangyi.nettyim.enums;

import lombok.Getter;
import lombok.ToString;

/**
 * @author lanfangyi@haodf.com
 * @version 1.0
 * @since 2019/9/4 1:00 PM
 */
@Getter
@ToString
public enum  MsgTypeEnum {

    KEEP_ALIVE(1, "心跳包");

    private int type;

    private String desc;

    MsgTypeEnum(int type, String desc) {
        this.type = type;
        this.desc =  desc;
    }
}
