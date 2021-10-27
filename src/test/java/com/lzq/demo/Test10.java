package com.lzq.demo;
import java.util.*;

public class Test10 {
    public static void main(String[] args) {

        String num = "1221";
        System.out.println(f(num));
    }

    public static int f(String s){
        int n = s.length();
        if (s.charAt(0) == '0') return 0;
        if (n <= 1) return n;
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n;i++){
            int index = i - 1;
            int pre = s.charAt(index - 1) - '0';
            int cur = s.charAt(index) - '0';

            if (s.charAt(index) == '0' ){
                if (pre >= 3) return 0;
                else dp[i] = dp[i - 2];
            }else if ((pre * 10 + cur) <= 26) {
                dp[i] = dp[i-1] + dp[i-2];
            }else{
                dp[i] = dp[i - 1];
            }
//            System.out.println(i+" " + dp[i]);
        }
        return dp[n];
    }

}
