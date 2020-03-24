package com.czxy.user.service;


import com.czxy.user.domain.User;

public interface UserService {
    User login(User user);

    User register(User user);
}
