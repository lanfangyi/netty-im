package com.lanfangyi.nettyim.constants;


import com.lanfangyi.nettyim.base.ErrorInfo;

/**
 * @author lanfangyi@haodf.com
 * @version 1.0
 * @since 2019/7/31 2:50 PM
 */
public class ErrorCode {

    private ErrorCode() {
    }

    public static final ErrorInfo SYSTEM_ERROR = new ErrorInfo(1001, "系统错误");

    public static final ErrorInfo DATABASE_ERROR = new ErrorInfo(1002, "数据库错误");

}
