package com.lzq.demo;

import java.util.PriorityQueue;

public class pinTeye {
    // 静态代码块对于定义在它之后的静态变量，可以赋值，但是不能访问.
    // 静态代码块 -> 非静态代码块 -> 构造方法)
    static {
        i = 3;
    }
    private static int i;
    int b;

    public pinTeye(int b) {
        this.b = b;
    }

    public static void main(String[] args) {
//        new PriorityQueue<>()
//        s.substring(left,right); [left, right);
        String s = "fa";
        System.out.println(s.substring(0,1));
    }

}
