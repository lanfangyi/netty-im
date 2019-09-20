package com.lanfangyi.nettyim.bean;

import com.lanfangyi.nettyim.constants.StatusConstant;
import com.lanfangyi.nettyim.utils.IdGetUtil;
import lombok.Data;
import lombok.Synchronized;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * @author lanfangyi@haodf.com
 * @version 1.0
 * @since 2019/9/2 5:13 PM
 */
@Data
@Table(name = "sendtasks")
public class SendTask extends BaseBean {

    @Column(name = "providerid")
    private Long providerId;

    @Column(name = "providertype")
    private String providerType;

    @Column(name = "receiveuserid")
    private Long receiveUserId;

    @Column(name = "receiveusertype")
    private String receiveUserType;

    /**
     * 1代表不需要call， 2代表需要
     */
    @Column(name = "iscall")
    private Integer isCall;

    /**
     * 尝试发送的次数
     */
    @Column(name = "trytimes")
    private Integer tryTimes;

    @Column(name = "data")
    private String data;

    @Column(name = "status")
    private Integer status;

    public SendTask(Long providerId, String providerType, Long receiveUserId, String receiveUserType, String data) {
        this.setId(IdGetUtil.get());
        this.providerId = providerId;
        this.providerType = providerType;
        this.receiveUserId = receiveUserId;
        this.receiveUserType = receiveUserType;
        this.status = StatusConstant.VALID;
        this.data = data;
        this.isCall = 1;
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
