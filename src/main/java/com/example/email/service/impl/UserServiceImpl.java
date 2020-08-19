package com.example.email.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.email.entity.User;
import com.example.email.mapper.UserMapper;
import com.example.email.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.email.utils.ErrorMessage;
import com.example.email.utils.ResponseMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Mr_zhaon
 * @since 2020-06-23
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired(required = false)
    private UserMapper userMapper;

    @Override
    public Map<String,Object> login(User user) {
        User userGet = userMapper.selectOne(new QueryWrapper<User>().eq("username",user.getUsername()).eq("password",user.getPassword()));
        if( userGet == null){
            return ResponseMap.sendMessage(1002,ErrorMessage.UserNameOrPwdError.getValue());
        } else {
            return ResponseMap.sendMessage(0,"登录成功");
        }
    }

    @Override
    public Map<String,Object> register(User user) {
        User userGet = userMapper.selectOne(new QueryWrapper<User>().eq("username",user.getUsername()));
        if( userGet == null){
            userMapper.insert(user);
            return ResponseMap.sendMessage(0,"注册成功");
        } else {
            return ResponseMap.sendMessage(1003,ErrorMessage.ExistUser.getValue());
        }
    }
}
