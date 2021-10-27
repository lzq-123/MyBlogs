package com.lzq.demo.动态代理;

public class SendMessageImpl implements SendMessage{
    @Override
    public void sendMessage(String msg) {
        System.out.println("send msg : " + msg);
    }

    @Override
    public void accMesssage(String msg) {
        System.out.println("accept msg " + msg );
    }
}
