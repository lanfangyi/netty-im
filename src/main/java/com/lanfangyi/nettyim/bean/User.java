package com.lanfangyi.nettyim.bean;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * @author lanfangyi@haodf.com
 * @version 1.0
 * @since 2019/8/20 10:21 PM
 */
@Data
@Table(name = "users")
public class User extends BaseBean {

    public static String DEFAULT_HEAD_IMG = "http://pic24.nipic.com/20120922/10898738_143746326185_2.jpg";

    /**
     * 用户名
     */
    @Column(name = "name")
    private String name;

    /**
     * 描述，比如联系医助
     */
    @Column(name = "password")
    private String password;

    /**
     * 状态
     */
    @Column(name = "status")
    private Integer status;

    /**
     * 头像
     */
    @Column(name = "headimg")
    private String headImg = DEFAULT_HEAD_IMG;

    private String userType;
}
