package com.lanfangyi.nettyim.bean;

import lombok.Data;

import java.util.Date;

/**
 * @author lanfangyi@haodf.com
 * @version 1.0
 * @since 2019/8/20 10:30 PM
 */
@Data
public class BaseBean {

    private Long id;

    private Date cTime;

    private Date uTime;
}
