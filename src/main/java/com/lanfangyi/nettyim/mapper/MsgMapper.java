package com.lanfangyi.nettyim.mapper;

import com.lanfangyi.nettyim.bean.Msg;

/**
 * @author lanfangyi@haodf.com
 * @version 1.0
 * @since 2020/1/7 9:57 AM
 */
public interface MsgMapper {

    /**
     * 通过ID查找消息
     *
     * @param id 消息ID
     * @return 消息数据
     */
    Msg findById(long id);

    /**
     * 保存消息
     *
     * @param msg 消息数据
     * @return 成功返回1，失败返回0
     */
    int insert(Msg msg);

    /**
     * 根据消息ID删除消息
     *
     * @param id 消息ID
     * @return 成功返回1，失败返回0
     */
    int deleteById(long id);

}
