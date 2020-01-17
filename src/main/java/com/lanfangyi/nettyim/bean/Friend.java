package com.lanfangyi.nettyim.bean;

import com.lanfangyi.nettyim.constants.StatusConstant;
import com.lanfangyi.nettyim.utils.IdGetUtil;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author lanfangyi@haodf.com
 * @version 1.0
 * @since 2019/9/2 5:13 PM
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Friend extends BaseBean {

    private Long userId;

    private String userType;

    private Long friendUserId;

    private String friendUserType;

    private Integer status;

    public Friend(Long userId, String userType, Long friendUserId, String friendUserType) {
        this.userId = userId;
        this.userType = userType;
        this.friendUserId = friendUserId;
        this.friendUserType = friendUserType;
        this.status = StatusConstant.VALID;
        this.setCTime(new Date());
        this.setUTime(new Date());
    }

}
