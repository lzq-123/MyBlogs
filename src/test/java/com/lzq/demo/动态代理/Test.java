package com.lzq.demo.动态代理;

public class Test {
    public static void main(String[] args) {
        SendMessage msg = (SendMessage) JdkProxyFactory.getProxy(new SendMessageImpl());
        msg.sendMessage("dasfa");
        msg.accMesssage("dasfafa");
    }
}
