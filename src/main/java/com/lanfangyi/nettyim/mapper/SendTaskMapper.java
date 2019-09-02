package com.lanfangyi.nettyim.mapper;

import com.lanfangyi.nettyim.bean.SendTask;
import com.lanfangyi.nettyim.bean.User;
import org.apache.ibatis.annotations.Param;


/**
 * @author lanfangyi@haodf.com
 * @version 1.0
 * @since 2019/8/20 10:18 PM
 */
public interface SendTaskMapper {

    int insert(SendTask sendTask);

    SendTask findById(Long id);

    Boolean updateStatus(Long id, Integer status);
}
