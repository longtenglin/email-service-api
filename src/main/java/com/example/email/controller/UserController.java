package com.example.email.controller;

import com.example.email.entity.User;
import com.example.email.service.impl.UserServiceImpl;
import com.example.email.utils.ErrorMessage;
import com.example.email.utils.ResponseMap;
import com.example.email.utils.SessionUtils;
import com.example.email.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class UserController {

    @Autowired(required = false)
    private UserServiceImpl userService;

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public Map<String,Object> login(HttpServletRequest request, @RequestBody User user){
        if (user.getCode().equals(SessionUtils.getVerifyCode(request))){
            return userService.login(user);
        } else {
            return ResponseMap.sendMessage(1001,ErrorMessage.VerifyCode.getValue());
        }
    }

    @RequestMapping(path="/register", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> register(HttpServletRequest request, @RequestBody User user){
        log.info("The Verification code obtained from the session is "+ SessionUtils.getVerifyCode(request));
        if (user.getCode().equals(SessionUtils.getVerifyCode (request))){
            return userService.register(user);
        } else {
            return ResponseMap.sendMessage(1001,ErrorMessage.VerifyCode.getValue());
        }
    }

    @RequestMapping(path = "/token", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getToken(){
        return TokenUtils.buildJWT("123456a");
    }

    @RequestMapping(path = "/info", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getInfo(){
        return ResponseMap.sendMessage("数据请求成功");
    }
    @RequestMapping(path = "/testHttp", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getTestHttp(){
        return ResponseMap.sendMessage("数据请求成功","不知道为什么请求不到啊");
    }
}
