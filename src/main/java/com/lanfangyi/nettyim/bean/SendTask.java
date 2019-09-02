package com.lanfangyi.nettyim.bean;

import com.lanfangyi.nettyim.constants.StatusConstant;
import com.lanfangyi.nettyim.utils.IdGetUtil;
import lombok.Data;

import java.util.List;

/**
 * @author lanfangyi@haodf.com
 * @version 1.0
 * @since 2019/9/2 5:13 PM
 */
@Data
public class SendTask extends BaseBean {

    private Long providerId;

    private String providerType;

    private Long receiveUserId;

    private String receiveUserType;

    /**
     * 1代表不需要call， 2代表需要
     */
    private Integer isCall;

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
        this.isCall = 1;
    }
}
