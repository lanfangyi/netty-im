package com.lanfangyi.nettyim.service.impl;

import com.lanfangyi.nettyim.mapper.UserMapper;
import com.lanfangyi.nettyim.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author lanfangyi@haodf.com
 * @version 1.0
 * @since 2019/8/21 11:06 PM
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public Long getUserByUsername(String username) {
        return null;
    }
}
