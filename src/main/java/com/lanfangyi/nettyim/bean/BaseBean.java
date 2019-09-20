package com.lanfangyi.nettyim.bean;

import com.lanfangyi.nettyim.utils.IdGetUtil;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * @author lanfangyi@haodf.com
 * @version 1.0
 * @since 2019/8/20 10:30 PM
 */
@Data
class BaseBean implements Serializable {

    @Id
    private Long id = IdGetUtil.get();

    @Column(name = "ctime")
    private Date cTime = new Date();

    @Column(name = "utime")
    private Date uTime = new Date();
}
