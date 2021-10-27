package com.lzq.demo;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.*;

public class Test9 {
    public static void main(String[] args) {

//        Random random = new Random();
//        for (int i = 0; i < 10; i++){
//            nums[i] = random.nextInt(100);
//        }

        int[][] nums = new int[10][];
        Arrays.sort(nums, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return 0;
            }
        });

        Queue<Integer> queue =  new PriorityQueue<>((a,b) -> a-b);
    }



//    public static int[][] split (int[][] intervals) {
//        // write code here
//        Arrays.sort(intervals, new Comparator<int[]>() {
//            @Override
//            public int compare(int[] o1, int[] o2) {
//                if (o1[0] == o2[0]) return o1[1] - o2[1];
//                else return o1[0] - o2[0];
//            }
//        });
//
//        int n = intervals.length;
//        for (int i = 0; i < n; i++){
//
//        }
//        return  0;
//    }

}
