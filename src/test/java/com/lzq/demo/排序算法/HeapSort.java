package com.lzq.demo.排序算法;

import java.util.Arrays;

public class HeapSort {
    public static void main(String[] args) {
        int[] nums = new int[]{4,6,8,5,9};
        heapSort(nums);
        System.out.println(Arrays.toString(nums));
    }

    public static void heapSort(int[] nums){
        int n = nums.length;
        // 升序建大顶堆
        for (int i = n / 2 - 1; i >= 0; i--){
            adjust(nums, n, i);
        }
        for (int length = n - 1; length > 0; length--){
            swap(nums, length, 0);
            adjust(nums, length, 0);
        }
    }

    // 建堆
    public static void adjust(int[] nums, int length, int i){
        int tmp = nums[i];
        // k 初始为 i的左子节点。
        for (int k = 2 * i + 1; k < length; k = 2 * k + 1){
            if (k + 1 < length && nums[k + 1] > nums[k]){
                // 如果右子节点大于左子节点的值，将k指向右子节点。
                k++;
            }
            if (nums[k] > tmp){
                nums[i] = nums[k];
                i = k;
            }else break;
        }
        nums[i] = tmp;
    }

    public static void swap(int[] nums, int l, int r){
        int tmp = nums[l];
        nums[l] = nums[r];
        nums[r] = tmp;
    }
}
