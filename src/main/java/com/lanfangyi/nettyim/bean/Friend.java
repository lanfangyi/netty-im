package com.lanfangyi.nettyim.bean;

import com.lanfangyi.nettyim.constants.StatusConstant;
import com.lanfangyi.nettyim.utils.IdGetUtil;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author lanfangyi@haodf.com
 * @version 1.0
 * @since 2019/9/2 5:13 PM
 */
@Data
@Table(name = "friends")
public class Friend extends BaseBean {

    @Column(name = "userid")
    private Long userId;

    @Column(name = "usertype")
    private String userType;

    @Column(name = "frienduserid")
    private Long friendUserId;

    @Column(name = "friendusertype")
    private String friendUserType;

    @Column(name = "status")
    private Integer status;

    public Friend(Long userId, String userType, Long friendUserId, String friendUserType) {
        this.setId(IdGetUtil.get());
        this.userId = userId;
        this.userType = userType;
        this.friendUserId = friendUserId;
        this.friendUserType = friendUserType;
        this.status = StatusConstant.VALID;
        this.setCTime(new Date());
        this.setUTime(new Date());
    }
}
