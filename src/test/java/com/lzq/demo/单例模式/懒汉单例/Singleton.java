package com.lzq.demo.单例模式.懒汉单例;

//  懒汉单例
public class Singleton {
    private Singleton() {};

    private volatile static Singleton single = null;

    public synchronized  static  Singleton getInstance(){
        if (single == null){
            synchronized (Singleton.class){
                if (single == null)  single = new Singleton();
            }

        }
        return  single;
    }
}


