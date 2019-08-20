package com.lanfangyi.nettyim.bean;

import lombok.Data;

/**
 * @author lanfangyi@haodf.com
 * @version 1.0
 * @since 2019/8/20 10:21 PM
 */
@Data
public class User extends BaseBean{

    public static String DEFAULT_HEAD_IMG = "http://pic24.nipic.com/20120922/10898738_143746326185_2.jpg";

    /**
     * 用户名
     */
    private String name;

    /**
     * 描述，比如联系医助
     */
    private String password;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 头像
     */
    private String headImg = DEFAULT_HEAD_IMG;
}
