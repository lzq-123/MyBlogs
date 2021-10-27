package com.lzq.demo.动态代理;

import java.lang.reflect.Proxy;

public class JdkProxyFactory {
    public static Object getProxy(Object target){
        // classLoader, getInterfaces, new InvocationHandler(InterfacesImpl)
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new MsgInvocationHandler(target));
    }
}
