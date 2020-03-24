package com.czxy.user.filter;

import com.czxy.throwable.LoginException;
import com.czxy.user.domain.User;
import com.czxy.utils.JwtUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.PublicKey;

@Component
public class LoginFilter implements HandlerInterceptor {

    @Resource
    private PublicKey publicKey;
    @Resource
    private ThreadLocal<User> threadLocal;

    private Logger logger = LoggerFactory.getLogger(LoginFilter.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        try {
            User user = JwtUtils.getObjectFromToken(token, publicKey, User.class);
            // 将user 存储在当前 线程中
            threadLocal.set(user);
            return true;
        } catch (Exception e){
            logger.info("用户未登录，返回主页");
            throw new LoginException("用户登录认证失败");
        }
    }
}
