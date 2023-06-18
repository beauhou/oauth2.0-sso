package com.oauth.core.handler;


import com.oauth.core.constant.HttpResultConstant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;

/**
 * 全局异常处理
 *
 * @author BeauHou
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 其他全局异常
     *
     * @param response
     * @param ex
     * @return
     */
    @ExceptionHandler(Exception.class)
    public HttpResultConstant globalException(HttpServletResponse response, Exception ex) {
        HttpResultConstant error = HttpResultConstant.error(ex.getMessage());
        logger.error("出现异常", ex);
        return error;
    }
}