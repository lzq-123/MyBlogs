package com.lzq.demo.้ๆไปฃ็;

public class Test {
    public static void main(String[] args) {
        SmsService service = new SmsServiceImpl();
        Proxy proxy = new Proxy(service);

        proxy.send("134514");
    }
}
