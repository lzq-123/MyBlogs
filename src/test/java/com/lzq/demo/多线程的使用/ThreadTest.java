package com.lzq.demo.多线程的使用;

public class ThreadTest {
    public static void main(String[] args) {
        MyThread t1 = new MyThread("线程一");
        MyThread t2 = new MyThread("线程二");
        MyThread t3 = new MyThread("线程三");
        t1.start();
        t2.start();
        t3.start();
    }
}
