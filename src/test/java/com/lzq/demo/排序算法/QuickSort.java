package com.lzq.demo.排序算法;

import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        int[] nums = new int[]{0,-1,5,3,2,0,1,5,1,2,0,-6,8};
        quicklySort(nums, 0, nums.length - 1);
        System.out.println(Arrays.toString(nums));
    }

    public static void quicklySort(int[] nums, int l, int r){
        if (l < r){
            int index = partitions(nums, l, r);
            quicklySort(nums, l, index - 1);
            quicklySort(nums, index + 1, r);
        }
    }

    public static int partitions(int[] nums, int l, int r){
           int index = l;
           while (l < r){
               while (l < r && nums[r] >= nums[index]){
                   r--;
               }
               while (l < r && nums[l] <= nums[index]){
                   l++;
               }
               if (l != r) swap(nums, l, r);
           }
           if (l != index) swap(nums, l, index);
           return l;
    }

    public static void swap(int[] nums, int l, int r){
        int tmp = nums[l];
        nums[l] = nums[r];
        nums[r] = tmp;
    }
}
