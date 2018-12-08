package com.itheima.exception;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SysExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        MyException myExc = null;
        if (ex instanceof MyException){
            myExc = (MyException)ex;
        }else {
            myExc = new MyException("请联系管理员");
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("msg",myExc.getMsg());
        modelAndView.setViewName("error");
        return modelAndView;

    }
}
