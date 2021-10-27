package com.lzq.demo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MsgInvocationHandler implements InvocationHandler {

    private Object target;

    public MsgInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("动态代理之前");
        Object invoke = method.invoke(target, args);
        System.out.println("动态代理之后");
        return invoke;
    }
}
