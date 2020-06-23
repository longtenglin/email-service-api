package com.example.email.service.impl;

import com.example.email.entity.User;
import com.example.email.mapper.UserMapper;
import com.example.email.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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

}
