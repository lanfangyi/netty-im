package com.lanfangyi.nettyim.service.impl;

import com.lanfangyi.nettyim.bean.User;
import com.lanfangyi.nettyim.constants.StatusConstant;
import com.lanfangyi.nettyim.mapper.UserMapper;
import com.lanfangyi.nettyim.service.UserService;
import com.lanfangyi.nettyim.utils.IdGetUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author lanfangyi@haodf.com
 * @version 1.0
 * @since 2019/8/21 11:06 PM
 */
@Service("userService")
@Slf4j
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User getUserByUsername(String username) {
        return userMapper.getUserByUsername(username);
    }

    @Override
    public User saveUser(String userName, String password) {
        User user = new User();
        user.setId(IdGetUtil.get());
        user.setName(userName);
        user.setPassword(password);
        user.setStatus(StatusConstant.VALID);
        userMapper.insert(user);
        return user;
    }

    @Override
    public User findUserById(long id) {
        return userMapper.getUserById(id);
    }

}
