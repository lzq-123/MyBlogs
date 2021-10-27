package com.lzq.demo;

import java.util.*;
import java.util.concurrent.ThreadPoolExecutor;

public class Test6 {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Random random = new Random();
        Student s1 = new Student(5, "lq");
        Student s2 = new Student(5, "lq");
        System.out.println(s1.hashCode());
        System.out.println(s2.hashCode());
//        for (int i = 0; i < 10; i++) {
//            int j = random.nextInt(2);
//            System.out.println(j);
//        }

    }


    public static int f(int x, int[] nums){
        int n = nums.length;
        int sum = 0;
        for (int i : nums){
            sum += i;
        }
        int target = sum - x;
        // 当x > sum的时候，直接return
        if (target < 0) return -1;
        int l = 0, r = 0;
        int arraySum = 0;
        int maxLen = 0;
        // sum -> [l, r) == target,
        while (r < n){
            arraySum += nums[r];
            r++;
            while (arraySum > target){
                arraySum -= nums[l];
                l++;
            }
            if (arraySum == target){
                System.out.println(l + " ： " +r);
                maxLen = Math.max(maxLen, r - l);
            }
        }
        return n - maxLen;
    }

}
