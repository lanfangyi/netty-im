package com.lanfangyi.nettyim.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author lanfangyi@haodf.com
 * @version 1.0
 * @since 2019/8/14 4:27 PM
 */
@Data
@AllArgsConstructor
public class ErrorInfo {

    private int code ;

    private String msg;

}
