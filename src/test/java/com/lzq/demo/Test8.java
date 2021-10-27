package com.lzq.demo;

import java.util.Scanner;

public class Test8 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()){
            int n = sc.nextInt();
            int k = sc.nextInt();
            int[] nums = new int[n];
            for (int i = 0; i < n; i++){
                nums[i] = sc.nextInt();
            }
            f(n, k, nums);
        }

    }

    public static void f(int n, int k, int[] nums){
        long yes = 0, no = 0;
        for (int i = 0; i < n - 1; i++){
            int tar = nums[i] + k;
            int index = find(nums, tar, i);
            yes += index - i;
            no += n - index - 1;

        }
        long all = yes + no;
        if (yes == 0) System.out.println("0/1");
        else {
            long num = gcb(all, yes);
            System.out.println(yes/num+"/"+all/num);
        }
    }

    public static int find(int[] nums, int tar, int l){
        int r = nums.length - 1;
        while (l < r){
            int mid = l + (r - l + 1) / 2;
            if (nums[mid] > tar){
                r = mid - 1;
            }else{
                l = mid;
            }
        }

        return l;
    }

    public static long gcb(long a, long b){
        while (a % b != 0){
            long tmp = a % b;
            a = b;
            b = tmp;
        }
        return b;
    }
}
