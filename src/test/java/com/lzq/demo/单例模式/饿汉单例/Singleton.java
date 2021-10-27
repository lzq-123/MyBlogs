package com.lzq.demo.单例模式.饿汉单例;

// 饿汉单例 在初始化的时候就已经创建好了
public class Singleton {
    public Singleton() {
    }
    // 在初始化类的时候就直接创建好一个静态的对象供系统使用
    private  static Singleton single = new Singleton();

    public Singleton getInstance(){
        return single;
    }
}
