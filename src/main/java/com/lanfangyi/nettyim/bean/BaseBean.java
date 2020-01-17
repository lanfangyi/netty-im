package com.lanfangyi.nettyim.bean;

import com.lanfangyi.nettyim.utils.IdGetUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * @author lanfangyi@haodf.com
 * @version 1.0
 * @since 2019/8/20 10:30 PM
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
class BaseBean implements Serializable {

    private Long id = IdGetUtil.get();

    private Date cTime = new Date();

    private Date uTime = new Date();
}
