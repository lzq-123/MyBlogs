package com.lzq.demo.静态代理;

public class Proxy implements SmsService{

    private SmsService service;

    public Proxy(SmsService service) {
        this.service = service;
    }

    @Override
    public void send(String msg) {
        System.out.println("方法执行前");
        service.send(msg);
        System.out.println("方法执行后");
    }
}
