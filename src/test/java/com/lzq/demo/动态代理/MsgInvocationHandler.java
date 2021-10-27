package com.lzq.demo.动态代理;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MsgInvocationHandler implements InvocationHandler {

    private  Object target;

    // 注入class
    public MsgInvocationHandler(Object target) {
        this.target = target;
    }
    // 实现invocationHandler 重写invoke方法
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before method : "+ method.getName());
        // 真正执行
        Object invoke = method.invoke(target, args);
        System.out.println("after method : "+ method.getName());
        return invoke;
    }
}
