package com.lzq.demo.静态代理;

public class Test {
    public static void main(String[] args) {
        SmsService service = new SmsServiceImpl();
        Proxy proxy = new Proxy(service);

        proxy.send("134514");
    }
}
