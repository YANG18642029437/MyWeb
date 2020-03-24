package com.czxy.controllerAdvice;

import com.czxy.throwable.LoginException;
import com.czxy.vo.BaseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 出现异常的统一处理
 */
@ControllerAdvice
@ResponseBody
public class ControllerExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());
    private static final String logExceptionFormat = "[EXIGENCE] Some thing wrong with the system: %s";

    @ExceptionHandler({LoginException.class})
    public BaseResult handleLoginException(LoginException ex){
        logger.info("使用统一异常处理");
        return BaseResult.error(ex.getMessage());
    }

    /**
     * 所有其他异常都进行处理
     * @return
     */
    @ExceptionHandler()
    public BaseResult handleException(Exception ex){
        return BaseResult.error(ex.getMessage());
    }


}
