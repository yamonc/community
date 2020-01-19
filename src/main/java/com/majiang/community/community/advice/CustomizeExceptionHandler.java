package com.majiang.community.community.advice;

import com.majiang.community.community.exception.CustomizeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @Created by Intellij IDEA.
 * @author: 陈亚萌
 * @Date: 2020/1/19
 */
@ControllerAdvice
public class CustomizeExceptionHandler {
    @ExceptionHandler(Exception.class)
    ModelAndView handle( Throwable ex, Model model) {
        if (ex instanceof CustomizeException){
            model.addAttribute("message",ex.getMessage());
        }else{
            model.addAttribute("message","服务冒烟了，请稍后重新试下！");
        }
        return new ModelAndView("error");
    }
}
