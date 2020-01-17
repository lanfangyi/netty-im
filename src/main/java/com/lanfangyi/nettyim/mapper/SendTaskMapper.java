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

    /**
     * 新增推送任务
     *
     * @param sendTask 推送任务数据
     * @return 影响的行数
     */
    int insert(SendTask sendTask);

    /**
     * 更具ID查询推送任务
     *
     * @param id 推送任务的ID
     * @return 推送任务数据
     */
    SendTask findById(long id);

    /**
     * 删除推送任务
     *
     * @param id 推送任务的ID
     * @return 返回影响的行数
     */
    int deleteById(long id);

    /**
     * 更新推送任务的状态
     *
     * @param id     推送任务的ID
     * @param status 要更新的状态
     * @return 更新成功返回true，失败返回false
     */
    Boolean updateStatus(@Param("id") long id, @Param("status") Integer status);
}
