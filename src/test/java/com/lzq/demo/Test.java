package com.lzq.demo;

import sun.misc.Launcher;

import java.net.URL;
import java.util.*;

public class Test {
    // 采用key, list<>的map方式存储
    private static Map<String, List<Object[]>> map = new HashMap<>();

    public static void main(String[] args) {
        System.out.println(String.class.getClassLoader());
        System.out.println(com.sun.crypto.provider.DESKeyFactory.class.getClassLoader().getClass().getName());
        ClassLoader appClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println("the appClassLoader : " + appClassLoader);
        URL[] urls = Launcher.getBootstrapClassPath().getURLs();
        for (int i = 0; i < urls.length; i++) {
            System.out.println(urls[i]);
        }
    }


    public static void set(String key, String value, int timestamp) {
        List<Object[]> list = map.getOrDefault(key, new ArrayList<Object[]>());
        list.add(new Object[]{value, timestamp});
        if (!map.containsKey(key)){
            map.put(key, list);
        }

    }

    public String get(String key, int timestamp) {
        List<Object[]> obj = map.get(key);
        int l = 0, r = obj.size() - 1;
        while (l <= r){
            int mid = l  + (r - l + 1) / 2;
            if ((int) obj.get(mid)[1] > timestamp){
                r = mid - 1;
            }else if ((int) obj.get(mid)[1] == timestamp){
                return (String) obj.get(mid)[0];
            }else {
                l = mid;
            }
        }
        return (String) obj.get(l)[0];
    }
}
