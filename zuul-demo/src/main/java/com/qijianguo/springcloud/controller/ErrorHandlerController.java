package com.qijianguo.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Zuul异常处理
 * @author qijianguo
 */
@RestController
public class ErrorHandlerController implements ErrorController {

    @Autowired
    private ErrorAttributes errorAttributes;
    @Override
    public String getErrorPath() {
        return "/error";
    }

    @RequestMapping("/error")
    public Object error(HttpServletRequest request) {

        Map<String, Object> errorAttributes = this.errorAttributes.getErrorAttributes(new ServletWebRequest(request), true);
        String message = (String)errorAttributes.get("message");
        String trace = (String)errorAttributes.get("trace");
        // TODO ...

        String result = "{\"code\": 500, \"message\": \"Try again later!\"}";
        return result;
    }
}
