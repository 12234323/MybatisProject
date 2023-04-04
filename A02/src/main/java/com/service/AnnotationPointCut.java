package com.service;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect //标注这个类是一个切面
public class AnnotationPointCut {
    @Before("execution(* com.service.UserServiceImpl.*(..))")
    public void before(){
        System.out.println("方法执行前");
    }

    @After("execution(* com.service.UserServiceImpl.*(..))")
    public void after(){
        System.out.println("方法执行后");
    }
    //环绕增强，我们可以确定一个参数，代表我们要获取处理切入点
    @Around("execution(* com.service.UserServiceImpl.*(..))")
    public void around(){
        System.out.println("环绕前");

    }

}
