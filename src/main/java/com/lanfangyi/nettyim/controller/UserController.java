package com.lanfangyi.nettyim.controller;

import com.alibaba.druid.support.json.JSONUtils;
import com.lanfangyi.nettyim.base.IMResponse;
import com.lanfangyi.nettyim.bean.User;
import com.lanfangyi.nettyim.constants.ErrorCode;
import com.lanfangyi.nettyim.constants.StatusConstant;
import com.lanfangyi.nettyim.mapper.UserMapper;
import com.lanfangyi.nettyim.utils.IdGetUtil;
import com.lanfangyi.service.paramcheck.annotation.Valid;
import com.lanfangyi.service.paramcheck.annotation.activeannotation.NotBlank;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.annotation.Resource;
import java.util.Date;

/**
 * @author lanfangyi@haodf.com
 * @version 1.0
 * @since 2019/8/20 10:44 PM
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Resource
    private UserMapper userMapper;

    @PostMapping("/registerOrLogin")
    @Valid
    public IMResponse<User> registerOrLogin(@NotBlank String username, @NotBlank String password) {
        User userByUsername = userMapper.getUserByUsername(username);
        if (userByUsername != null) {
            if (userByUsername.getPassword().equals(password)) {
                return IMResponse.success(userByUsername);
            } else {
                return IMResponse.error(ErrorCode.PASSWORD_NOT_CURRECT, null);
            }
        }
        User user = new User();
        user.setId(IdGetUtil.get());
        user.setName(username);
        user.setPassword(password);
        user.setStatus(StatusConstant.VALID);
        userMapper.insert(user);

        log.info("Create user. user:{}", user);
        return IMResponse.success(user);
    }

    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE);
        System.out.println(2147483647);
    }

}
