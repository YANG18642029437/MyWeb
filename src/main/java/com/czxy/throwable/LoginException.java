package com.czxy.throwable;

/**
 * 登录异常错误
 */
public class LoginException extends Exception {
    public LoginException(String message) {
        super(message);
    }
}
