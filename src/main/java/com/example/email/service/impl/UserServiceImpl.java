package com.example.email.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.email.entity.User;
import com.example.email.mapper.UserMapper;
import com.example.email.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public String login(User user) {
        User userGet = userMapper.selectOne(new QueryWrapper<User>().eq("username",user.getUsername()).eq("password",user.getPassword()));
        if( userGet != null){
            return "用户名或密码错误";
        } else {
            return "登录成功";
        }

    }
}
