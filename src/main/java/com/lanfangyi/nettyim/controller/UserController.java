package com.lanfangyi.nettyim.controller;

import com.lanfangyi.nettyim.base.IMResponse;
import com.lanfangyi.nettyim.bean.User;
import com.lanfangyi.nettyim.constants.ErrorCode;
import com.lanfangyi.nettyim.service.UserService;
import com.lanfangyi.service.paramcheck.annotation.Valid;
import com.lanfangyi.service.paramcheck.annotation.activeannotation.NotBlank;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.annotation.Resource;

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
    private UserService userService;

    @PostMapping("/registerOrLogin")
    @Valid
    public IMResponse<User> registerOrLogin(@NotBlank String userName, @NotBlank String password) {
        User userByUsername = userService.getUserByUsername(userName);
        if (userByUsername != null) {
            if (userByUsername.getPassword().equals(password)) {
                return IMResponse.success(userByUsername);
            } else {
                return IMResponse.error(ErrorCode.PASSWORD_NOT_CURRECT, null);
            }
        }

        User user = userService.saveUser(userName, password);

        log.info("Create user. user:{}", user);
        return IMResponse.success(user);
    }
}
