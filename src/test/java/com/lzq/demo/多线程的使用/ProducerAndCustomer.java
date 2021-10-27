package com.lzq.demo.多线程的使用;

import java.util.List;
import java.util.Queue;
import java.util.Random;

public class ProducerAndCustomer {
}

class Producer extends Thread{
    private int maxSize = 10;
    private List<Integer> list;
    private String key =  "a";

    public Producer(int maxSize, List<Integer> list){
        this.maxSize = maxSize;
        this.list = list;
    }

    public void run(){
        while (true){
            if (list.size() < maxSize){
                synchronized (key){
                    list.add(new Random().nextInt(100));
                }
            }

        }

    }
}
