package com.lzq.demo.多线程的使用;

public class MyThread extends Thread {
    public MyThread(String name) {
        super(name);// 给线程名字赋值
    }
    static int tick = 1000;
//    private String key = new String( "aa");
    private String key = "aa";
    public void run(){
        while (tick > 0){
            synchronized(key){
                if (tick > 0){
                    System.out.println(getName() + " 卖出了第 " +tick+"张票");
                    tick--;
//                    key.notify();
                    try {
                        key.wait(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }

    }
}
