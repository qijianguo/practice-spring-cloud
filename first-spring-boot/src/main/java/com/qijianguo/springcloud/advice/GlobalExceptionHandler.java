package com.qijianguo.springcloud.advice;

import com.qijianguo.springcloud.model.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常拦截器
 * @author qijianguo
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result defaultException(HttpServletRequest request, Exception e) {
        Result result = new Result();
        if (e instanceof NoHandlerFoundException) {
            result.setCode(400);
            result.setMessage("Source not found.");
        } else {
            result.setCode(500);
            result.setMessage("System error, please try again later.");
        }
        result.setData(null);
        return result;
    }
}
