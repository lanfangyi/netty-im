package com.lanfangyi.nettyim.base;

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
public class IMResponse<T> implements Serializable {

    @ApiModelProperty(value = "状态码", example = "0")
    private int code;

    @ApiModelProperty(value = "相关信息", example = "success")
    private String msg;

    @ApiModelProperty(value = "请求的唯一标识", example = "0")
    private String reqNo;

    @ApiModelProperty(value = "请求返回的数据", example = "a")
    private T dataBody;

    public static <T> IMResponse<T> success(T dataBody) {
        IMResponse<T> response = new IMResponse<>();
        response.setDataBody(dataBody);
        return response;
    }

    public static <T> IMResponse<T> error(int code, String msg, T dataBody) {
        IMResponse<T> response = new IMResponse<>();
        response.setCode(code);
        response.setMsg(msg);
        response.setDataBody(dataBody);
        return response;
    }

    public static <T> IMResponse<T> error(int code, T dataBody) {
        IMResponse<T> response = new IMResponse<>();
        response.setCode(code);
        response.setDataBody(dataBody);
        return response;
    }

    public static <T> IMResponse<T> error(ErrorInfo errorInfo, T dataBody) {
        IMResponse<T> response = new IMResponse<>();
        response.setCode(errorInfo.getCode());
        response.setMsg(errorInfo.getMsg());
        response.setDataBody(dataBody);
        return response;
    }
}
