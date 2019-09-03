package com.lanfangyi.nettyim.future.resp;

import org.springframework.http.HttpStatus;

/**
 * @author lanfangyi@haodf.com
 * @version 1.0
 * @since 2019/9/2 5:59 PM
 */
public class SendMsgFutureResp<T> {

    private HttpStatus code  = HttpStatus.OK;

    private String msg = "";

    private T data;

    public static SendMsgFutureResp getDefaultSendMsgFutureResp() {
        return new SendMsgFutureResp();
    }

    public HttpStatus getCode() {
        return code;
    }

    public SendMsgFutureResp setCode(HttpStatus code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public SendMsgFutureResp setMsg(String msg) {
        this.msg = msg;
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
