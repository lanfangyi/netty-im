package com.lanfangyi.nettyim.base;

import com.lanfangyi.service.paramcheck.resp.BaseResponse;
import lombok.Data;

/**
 * @author lanfangyi@haodf.com
 * @version 1.0
 * @since 2019/8/14 3:34 PM
 */
@Data
public class IMResponse<T> extends BaseResponse {

    public static <T> IMResponse<T> success(T dataBody) {
        IMResponse<T> response = new IMResponse<>();
        response.setDataBody(dataBody);
        return response;
    }

    public static <T> IMResponse<T> error(int code, String msg, T dataBody) {
        IMResponse<T> response = new IMResponse<>();
        response.setCode(code);
        response.setMessage(msg);
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
        response.setMessage(errorInfo.getMsg());
        response.setDataBody(dataBody);
        return response;
    }
}
