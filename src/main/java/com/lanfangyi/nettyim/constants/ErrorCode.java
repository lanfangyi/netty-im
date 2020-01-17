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

    public static final ErrorInfo USERNAME_ALREDY_EXIST = new ErrorInfo(1003, "用户名已存在");

    public static final ErrorInfo PASSWORD_NOT_CURRECT = new ErrorInfo(1004, "用户名或密码错误");

    public static final ErrorInfo USER_NOT_EXIST = new ErrorInfo(1005, "用户不存在");

    public static final ErrorInfo FRIEND_HAS_EXIST = new ErrorInfo(1006, "朋友已存在");

}
