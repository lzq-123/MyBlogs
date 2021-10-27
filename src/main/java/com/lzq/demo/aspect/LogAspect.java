package com.lzq.demo.aspect;

import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogAspect {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* com.lzq.demo.controller.*.*(..))")
    public void log(){
    }

    @Before("log()")
    public void doBefore(){
        logger.info("-------doBefore----");
    }

    @After("log()")
    public void afterBefore(){
        logger.info("-----doAfter-----");
    }

//    @AfterReturning(returning = "result", pointcut = "log()")
//    public void doAfterReturn(Object result){
//
//    }

}
