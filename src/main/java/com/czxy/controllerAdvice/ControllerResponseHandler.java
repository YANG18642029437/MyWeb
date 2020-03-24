package com.czxy.controllerAdvice;

import com.czxy.user.domain.User;
import com.czxy.utils.JwtUtils;
import com.czxy.vo.BaseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.annotation.Resource;
import java.security.PrivateKey;

@ControllerAdvice
public class ControllerResponseHandler implements ResponseBodyAdvice<Object> {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private PrivateKey privateKey;
    @Resource
    private ThreadLocal<User> threadLocal;

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    /**
     * 当一切正常时使用的返回方法
     * @param o
     * @param methodParameter
     * @param mediaType
     * @param aClass
     * @param serverHttpRequest
     * @param serverHttpResponse
     * @return
     */
    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        logger.info("使用统一返回值处理");
        String token = null;
        try {
            if (threadLocal.get() == null){
                return o;
            }
            token = JwtUtils.generateToken(threadLocal.get(),30,privateKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 判断返回 的object 是否为 BaseRest 如果是直接返回 如果不是就返回 BaseRest
        if (o instanceof BaseResult){
            ((BaseResult) o).append("token",token);
            return o;
        }
        return BaseResult.ok("操作成功",o).append("token",token);
    }

}
