package com.czxy.user.service.impl;


import com.czxy.user.dao.UserMapper;
import com.czxy.user.domain.User;
import com.czxy.user.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.UUID;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User login(User user) {
        if (user.getUsername() != null && user.getPassword() != null)
            return userMapper.selectOne(user);
        else
            return null;
    }

    @Override
    public User register(User user) {
        // 判断用户名是否有相同的
        User param = new User();
        param.setUsername(user.getUsername());
        int count = userMapper.selectCount(param);
        if (count != 0) {
            throw new RuntimeException("用户名已经存在");
        }

        user.setName("Y" + user.getUsername());
        user.setUid(UUID.randomUUID().toString().replace("-", ""));
        userMapper.insertSelective(user);
        return user;
    }
}
