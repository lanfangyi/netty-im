package com.lanfangyi.nettyim.future.resp;

import org.springframework.http.HttpStatus;

/**
 * @author lanfangyi@haodf.com
 * @version 1.0
 * @since 2019/9/2 5:59 PM
 */
public class SendMsgFutureResp<T> {

    private HttpStatus status  = HttpStatus.OK;

    private T data;

    public static SendMsgFutureResp getDefaultSendMsgFutureResp() {
        return new SendMsgFutureResp();
    }

    public HttpStatus getStatus() {
        return status;
    }

    public SendMsgFutureResp setCode(HttpStatus status) {
        this.status = status;
        return this;
    }

    public T getData() {
        return data;
    }

    public SendMsgFutureResp setData(T data) {
        this.data = data;
        return this;
    }
}
