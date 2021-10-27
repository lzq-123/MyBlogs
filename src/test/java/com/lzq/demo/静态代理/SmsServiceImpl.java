package com.lzq.demo.静态代理;

public class SmsServiceImpl implements SmsService{

    @Override
    public void send(String msg) {
        System.out.println("sendMsg : " + msg);
    }
}
