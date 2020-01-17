package com.lanfangyi.nettyim.service;

import com.lanfangyi.nettyim.bean.User;

import java.util.List;
import java.util.Map;

/**
 * @author lanfangyi@haodf.com
 * @version 1.0
 * @since 2019/8/21 11:06 PM
 */
public interface UserService {

    User getUserByUsername(String username);

    User saveUser(String userName, String password);

    User findUserById(long id);

    List<User> listUser(List<Long> userIds);
}
