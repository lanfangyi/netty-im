package com.lanfangyi.nettyim.vo.responsevo;

import com.lanfangyi.nettyim.bean.Friend;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author lanfangyi@haodf.com
 * @version 1.0
 * @since 2020/1/10 3:32 PM
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class FriendVo {

    private Long friendUserId;

    private String friendUserType;

    private String friendUserName;

    private String headImg;

    public FriendVo(Friend friend) {
        this.friendUserId = friend.getUserId();
        this.friendUserType = friend.getUserType();
    }
}
