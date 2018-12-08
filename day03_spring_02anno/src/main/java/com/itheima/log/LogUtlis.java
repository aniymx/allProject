package com.itheima.log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component("log")
@Aspect
public class LogUtlis {

    @Pointcut("execution(* com.itheima.service.impl.*.*(..))")
    public void pt1(){}
    @Before("pt1()")
    public void beforeLog() {
        System.out.println("beforeLog ...init...");
    }
@AfterReturning("pt1()")
    public void afterRunning() {
        System.out.println("afterRunning ...start...");
    }
@AfterThrowing("pt1()")
    public void excepetion() {
        System.out.println("excepetion ...start...");
    }
@After("pt1()")
    public void after() {
        System.out.println("after ...start...");
    }
//@Around("pt1()")
    public void around(ProceedingJoinPoint pdj) {
        Object proceed = null;
        try {
            //获得执行需要的参数
            Object[] args = pdj.getArgs();

            System.out.println("执行之前");
            //执行方法
            proceed = pdj.proceed(args);
            System.out.println("执行之后");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            System.out.println("执行异常");
        } finally {
            System.out.println("总会执行finally");
        }

    }
}
