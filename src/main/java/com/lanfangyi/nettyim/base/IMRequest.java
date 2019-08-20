package com.lanfangyi.nettyim.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author lanfangyi@haodf.com
 * @version 1.0
 * @since 2019/8/14 3:34 PM
 */
@Getter
@Setter
@ToString
public class IMRequest implements Serializable {

    @ApiModelProperty(value = "唯一请求号", example = "1234567890")
    private String reqNo;

    @ApiModelProperty(value = "自定义协议的标示", example = "myProtocol")
    private String protocol;

    @ApiModelProperty(value = "请求的时间戳", example = "0")
    private int timeStamp;

}
