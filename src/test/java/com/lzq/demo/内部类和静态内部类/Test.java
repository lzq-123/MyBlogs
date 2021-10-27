package com.lzq.demo.内部类和静态内部类;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        int p = 5;
        int i = 1;
        int VAL = (int) Math.pow(10, 9) + 7;
        int max = (int) Math.pow(2, p) - 1;
//        long res = (long) Math.pow(max - 1, max / 2);
//        res = (res * max) % VAL;
        long res = max - 1;
        for (; i < max / 2; i *= 2){
            res = ((long) Math.pow(res, 2)) % VAL;
        }

        System.out.println(res);
    }
}
