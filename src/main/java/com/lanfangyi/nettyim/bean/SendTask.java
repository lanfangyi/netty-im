package com.lanfangyi.nettyim.bean;

import com.lanfangyi.nettyim.constants.StatusConstant;
import com.lanfangyi.nettyim.utils.IdGetUtil;
import io.swagger.models.auth.In;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Synchronized;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * @author lanfangyi@haodf.com
 * @version 1.0
 * @since 2019/9/2 5:13 PM
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class SendTask extends BaseBean {

    private Long providerId;

    private String providerType;

    private Long receiveUserId;

    private String receiveUserType;

    /**
     * 1代表不需要call， 2代表需要
     */
    private Integer isCall;

    /**
     * 尝试发送的次数
     */
    private Integer tryTimes;

//    /**
//     * 消息类型
//     */
//    private Integer type;

    private String data;

    private Integer status;

    public SendTask(Long providerId, String providerType, Long receiveUserId, String receiveUserType, String data) {
        this.setId(IdGetUtil.get());
        this.providerId = providerId;
        this.providerType = providerType;
        this.receiveUserId = receiveUserId;
        this.receiveUserType = receiveUserType;
        this.status = StatusConstant.VALID;
        this.data = data;
        this.isCall = 0;
        this.tryTimes = 0;
    }

    @Synchronized
    public int increaseTryTimes() {
        if (this.tryTimes == null) {
            this.tryTimes = 0;
        }
        this.tryTimes += 1;
        return this.tryTimes;
    }

    public String getUserKey() {
        return this.receiveUserId + "_" + this.receiveUserType;
    }
}
