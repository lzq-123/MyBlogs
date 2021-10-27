package com.lzq.demo;

import java.util.*;

public class Test7 {
    public static void main(String[] args) {
        int[][] p = new int[4][5];
        Arrays.sort(p, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) return o1[1] - o2[1];
                else return o1[0] - o2[0];
            }
        });
        int m = p.length;
        int n = 2;
        int aMax = 0, bMax = 0;
        int res = 0;
        int lastMax = 0;
        for (int i = m - 1; i >= 0; i--){
            int a = p[i][0], b = p[i][1];

            if (p[i][0] != p[i+1][0] || i == m - 1){
                bMax = Math.max(bMax, lastMax);
                lastMax = p[i][1];
            }
            if (p[i][1] < bMax){
                res++;
            }
        }
    }


}
