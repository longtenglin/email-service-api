package com.example.email.service;

import com.example.email.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Mr_zhaon
 * @since 2020/06/23
 */
public interface IUserService extends IService<User> {

    Map<String,Object> login(User user);

    Map<String,Object> register(User user);
}
