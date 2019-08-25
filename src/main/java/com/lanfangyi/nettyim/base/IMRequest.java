package com.lanfangyi.nettyim.base;

import com.lanfangyi.service.paramcheck.req.BaseRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author lanfangyi@haodf.com
 * @version 1.0
 * @since 2019/8/14 3:34 PM
 */
@Data
public class IMRequest extends BaseRequest {

    @ApiModelProperty(value = "自定义协议的标示", example = "myProtocol")
    private String protocol;

}
