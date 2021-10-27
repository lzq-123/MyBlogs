package com.lzq.demo;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.*;

public class test1 {
    public static void main(String[] args) {
        List<Integer> path = new ArrayList<>();
        boolean add = new ArrayList<>(path).add(1);
//        path.contains();
    }
    public static boolean isCovered(int[][] ranges, int left, int right) {
        Arrays.sort(ranges, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        int n = ranges.length;
        int maxNum = 0;
        if (ranges[0][0] > left || ranges[n-1][1] < right) return false;
        for (int i = 0; i < n; ++i){
            if (ranges[i][0] <= left && ranges[i][1] >= left){
                // 判断当前数组是否连续
                maxNum = ranges[i][1];
                for (int j = i; j < n; ++j){

                    if (maxNum >= right) return true;
                    // 说明当前数组不连续
                    if (ranges[j][0] - maxNum > 1) return false;
                    maxNum = Math.max(maxNum, ranges[j][1]);
                }
            }
        }
        return false;

    }

    public String maximumTime(String time) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < time.length(); ++i){
            if (time.charAt(i) == '?'){
                if (i == 0) sb.append('2');
                else if (i == 4) sb.append('9');
                else if (i == 3) sb.append('5');
                else if (i == 1){
                    if ((char) sb.charAt(i) == '2') sb.append('4');
                    else sb.append('9');
                }
            }else{
                sb.append(time.charAt(i));
            }
        }
        return sb.toString();
    }

    public static int[] restoreArray(int[][] adjacentPairs) {

        int n = adjacentPairs.length;
        if (n <= 1) return adjacentPairs[0];
        for (int[] ins : adjacentPairs){
            System.out.println(Arrays.toString(ins));
        }
        int[] res = new int[n];
        boolean[] booleans = new boolean[n];
        Deque<Integer> queue = new LinkedList<>();
        Arrays.sort(adjacentPairs, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) return o1[1] - o2[1];
                else return o1[0] - o2[0];
            }
        });
        for (int i = 0; i < n; ++i){
            if (queue.isEmpty()){
                queue.addLast(adjacentPairs[i][0]);
                queue.addLast(adjacentPairs[i][1]);
                booleans[i] = true;
            }else if (booleans[i] == false){
                for (int j = i; j < n; ++j){
                    booleans[j] = true;
                    if (adjacentPairs[j][0] == queue.peekFirst()) queue.addFirst(adjacentPairs[j][1]);
                    else if (adjacentPairs[j][0] == queue.peekLast()) queue.addLast(adjacentPairs[j][1]);
                    else if (adjacentPairs[j][1] == queue.peekFirst()) queue.addFirst(adjacentPairs[j][0]);
                    else if (adjacentPairs[j][1] == queue.peekLast()) queue.addLast(adjacentPairs[j][0]);
                    else {
                        booleans[j] = false;
                    }

                }
            }
        }
        int i = 0;
        while (!queue.isEmpty()){
            res[i++] = queue.pollFirst();
        }
        return res;
    }

    public static List<List<Integer>> verticalTraversal(TreeNode root) {
        int[] ints = new int[10];
        String s = "saf";

        List<List<Integer>> res = new ArrayList<>();
        Map<Integer, List<Integer>> map = new HashMap<>();
        Set<Integer> integers = map.keySet();
        List<Integer> list = new ArrayList<>();
        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return ints[o1] - ints[o2];
            }
        });

        Integer[] integers1 = new Integer[10];
        Arrays.sort(integers1, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return 0;
            }
        });

        return res;
    }
}
