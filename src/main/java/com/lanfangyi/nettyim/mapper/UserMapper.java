package com.lanfangyi.nettyim.mapper;

import com.lanfangyi.nettyim.bean.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @author lanfangyi@haodf.com
 * @version 1.0
 * @since 2019/8/20 10:18 PM
 */
public interface UserMapper {

    int insert(User user);

    Long getUserIdByUsername(@Param("username") String username);

    User getUserByUsername(@Param("username") String username);

    User getUserByUsername(@Param("username") String username, @Param("password") String password);

    User getUserById(long id);

    List<User> listUser(List<Long> userIds);
}
