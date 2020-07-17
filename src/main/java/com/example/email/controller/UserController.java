package com.example.email.controller;


import com.example.email.entity.User;
import com.example.email.service.impl.UserServiceImpl;
import com.example.email.utils.ResponseMap;
import com.example.email.utils.UserInfo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Mr_zhaon
 * @since 2020-06-23
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    private UserServiceImpl userService;

    @RequestMapping(path = "/login")
    public Map<String,Object> login(HttpServletRequest request, @RequestBody User user){
        if (user.getCode().equals(UserInfo.getVerifyCode(request))){
            String msg = userService.login(user);
            return ResponseMap.sendMessage(msg);
        } else {
            return ResponseMap.sendMessage("验证码错误");
        }
    }
}
