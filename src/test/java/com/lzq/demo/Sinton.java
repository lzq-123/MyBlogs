package com.lzq.demo;

public class Sinton {
    private volatile static Sinton sinton;

    public static Sinton getSinton(){
        if (sinton == null){
            synchronized (Sinton.class){
                if (sinton == null){
                    sinton = new Sinton();
                }
            }
        }
        return sinton;
    }
}
