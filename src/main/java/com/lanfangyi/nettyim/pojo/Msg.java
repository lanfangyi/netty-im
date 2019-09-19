package com.lanfangyi.nettyim.pojo;

import lombok.Data;

/**
 * @author lanfangyi@haodf.com
 * @version 1.0
 * @since 2019/9/9 7:19 PM
 */
@Data
public class Msg {

    private Integer msgType;

    private String content;
}
