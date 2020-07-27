package com.example.email.controller;


import com.example.email.entity.User;
import com.example.email.service.impl.UserServiceImpl;
import com.example.email.utils.ResponseMap;
import com.example.email.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
@RequestMapping("/api")
public class UserController {

    @Autowired(required = false)
    private UserServiceImpl userService;

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public Map<String,Object> login(HttpServletRequest request, @RequestBody User user){
        if (user.getCode().equals(SessionUtils.getVerifyCode(request))){
            String msg = userService.login(user);
            return ResponseMap.sendMessage(msg);
        } else {
            return ResponseMap.sendMessage("验证码错误");
        }
    }

    @RequestMapping(path="/register", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> register(HttpServletRequest request, @RequestBody User user){
        System.out.println("注册参数:____"+user);
        System.out.println("获取session中的验证码---Get VerifyCode from session:________"+SessionUtils.getVerifyCode(request));
        if (user.getCode().equals(SessionUtils.getVerifyCode(request))){
            String msg = userService.register(user);
            return ResponseMap.sendMessage(msg);
        } else {
            return ResponseMap.sendMessage(1001,"验证码错误");
        }
    }
}
