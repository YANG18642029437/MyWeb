package com.czxy.user.controller;

import com.czxy.user.domain.User;
import com.czxy.user.service.UserService;
import com.czxy.utils.JwtUtils;
import com.czxy.vo.BaseResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.security.PrivateKey;

@RestController
@RequestMapping("user")
public class UserController {
    @Resource
    private UserService userService;
    @Resource
    private PrivateKey privateKey;

    @PostMapping("login")
    public BaseResult login(@RequestBody User user) throws Exception {
        User loginUser = userService.login(user);
        if (loginUser != null){
            String token = JwtUtils.generateToken(loginUser, 30, privateKey);
            return BaseResult.ok("登录成功",token);
        }else
            return BaseResult.error("用户名或者密码失败");
    }

    @PostMapping("register")
    public BaseResult register(@RequestBody User user){
        try {
            User loginUser = userService.register(user);
            return BaseResult.ok("注册成功，请去登录");
        }catch (Exception e){
            return BaseResult.error(e.getMessage());
        }


    }

    @GetMapping("add")
    public BaseResult add(){
        return BaseResult.ok("打你哦");
    }

}


