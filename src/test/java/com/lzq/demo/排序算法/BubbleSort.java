package com.lzq.demo.排序算法;

import java.util.Arrays;

public class BubbleSort {
    public static void main(String[] args) {
        int[] nums = new int[]{0,-1,5,3,2,0,1,5,1,2,0,-6,8};
        bubbleSort((nums));
        System.out.println(Arrays.toString(nums));
    }

    public static void bubbleSort(int[] nums){
        int n = nums.length;
        for (int i = 0; i < n; i++){
            for (int j = n - 1; j > i; j--){
                if (nums[j] < nums[j - 1]) swap(nums, j, j - 1) ;
            }
        }
    }

    public static void swap(int[] nums, int l, int r){
        int tmp = nums[l];
        nums[l] = nums[r];
        nums[r] = tmp;
    }
}
