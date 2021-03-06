package com.product.judge.common.config;

import com.product.judge.common.exception.UserNotExistException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class MyExceptionHandler
{
    @ExceptionHandler(UserNotExistException.class)
    public String handleException(Exception e, HttpServletRequest request)
    {
        Map<String, Object> map = new HashMap<>();
        request.setAttribute("javax.servlet.error.status_code", 500);
        map.put("code", "user.notexist");
        map.put("message", "用户出错啦");
        request.setAttribute("ext", map);
        //转发到/error
        return "forward:/error";
    }
}
