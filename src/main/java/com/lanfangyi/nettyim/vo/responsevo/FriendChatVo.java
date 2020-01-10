package com.lanfangyi.nettyim.vo.responsevo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author lanfangyi@haodf.com
 * @version 1.0
 * @since 2020/1/6 9:00 PM
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class FriendChatVo implements Serializable {

    private Long id;

    private String friendUserName;

    private String latestMsg;

    private String headImgUrl;

    private String time;

    public FriendChatVo(long id, String latestMsg, String headImgUrl, String time) {
        this.id = id;
        this.latestMsg = latestMsg;
        this.headImgUrl = headImgUrl;
        this.time = time;
    }

}
