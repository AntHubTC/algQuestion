package com.tc.alg.dfs;

public class StairAlgIssue {

    public static void main(String[] args) {
        /**
         * 问题：上台阶可以一步上，也可以两步上，求第N个台阶有几种方法。
         *
         * 拿到问题开始分析：
         * 要求第N个台阶有几种方法，可以使用动态规划的方法来解决。设dp[i]表示到达第i个台阶的方法数，则有以下递推关系：
         * 当i = 1时，只有一种方法到达第一个台阶，即dp[1] = 1；
         * 当i = 2时，有两种方法到达第二个台阶，可以一步一步上或者一次性上两步，即dp[2] = 2；
         * 当i > 2时，到达第i个台阶的方法数为到达第i-1个台阶的方法数加上到达第i-2个台阶的方法数，即dp[i] = dp[i-1] + dp[i-2]。
         * 因此，可以通过填充数组dp来求解第N个台阶的方法数。
         */
        int targetNth = 10;
        // long result = countWaysToNthStair1(targetNth);
        long result = countWaysToNthStair2(targetNth);
        System.out.printf("到达第%s个台阶有%s种方法%n", targetNth, result);
    }


    // 递归求解(效率不高，会有很多重复计算)
    public static long countWaysToNthStair1(int n) {
        // 如果N为1，只有一种方法到达第一个台阶
        if (n == 1) return 1;
        // 如果N为2，有两种方法到达第二个台阶
        if (n == 2) return 2;

        return countWaysToNthStair1(n - 1) + countWaysToNthStair1(n - 2);
    }

    // 递推求解
    public static long countWaysToNthStair2(int n) {
        // 如果N为1，只有一种方法到达第一个台阶
        if (n == 1) return 1;
        // 如果N为2，有两种方法到达第二个台阶
        if (n == 2) return 2;

        // 创建一个数组来存储每个台阶的方法数(最好使用long来进行存储，int很快就要超长)
        long[] dp = new long[n + 1];
        dp[1] = 1;
        dp[2] = 2;

        // 使用循环填充数组，根据递推关系dp[i] = dp[i-1] + dp[i-2]来计算到达每个台阶的方法数
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        // 返回第N个台阶的方法数
        return dp[n];
    }

}
