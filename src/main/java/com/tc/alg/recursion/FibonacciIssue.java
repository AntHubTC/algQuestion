package com.tc.alg.recursion;

import com.tc.datastruct.TreeNode;
import com.tc.utl.Utils;
import com.tc.viewer.JFrameShow;
import com.tc.viewer.TreeTextVisualizer;

import java.awt.*;

public class FibonacciIssue {
    public static void main(String[] args) {
        /**
         * 问题：斐波那契数列指的是这样一个数列 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233，
         * 377，610，987，1597，2584，4181，6765，10946，17711，28657，46368……
         * 第n项是多少？
         */
        int targetNum = 20;
        // long result = fibonacci_v1(targetNum);
//        long result = fibonacci_v1_view_main(targetNum);
        // long result = fibonacci_v2(targetNum, new long[targetNum + 1]);
         long result = fibonacci_v2_view_main(targetNum, new long[targetNum + 1]);
        // long result = fibonacci_v3(targetNum);
        System.out.println(result);
    }

    // 简单递归(效率较低)
    public static long fibonacci_v1(int n) {
        if ((n == 0) || (n == 1))
            return n;
        return fibonacci_v1(n - 1) + fibonacci_v1(n - 2);
    }

    public static long fibonacci_v1_view_main(int n) {
        // 算法格子可视化
        TreeNode treeNode = new TreeNode("F[n]=F[n-1]+F[n-2]");
        TreeTextVisualizer visualizer = new TreeTextVisualizer(treeNode);
        visualizer.setPreferredSize(new Dimension(800, 1000));
        JFrameShow jFrameShow = new JFrameShow(visualizer);
        jFrameShow.show();

        // 开启gif记录
        visualizer.startGif("FibonacciIssue.gif");
        Utils.sleepMilliSeconds(200L);

        long result = fibonacci_v1_view(n, visualizer, treeNode);

        // 完成gif记录
        visualizer.finishGif();
        // jFrameShow.close();

        return result;
    }

    public static long fibonacci_v2_view_main(int n, long[] dict) {
        // 算法格子可视化
        TreeNode treeNode = new TreeNode("F[n]=F[n-1]+F[n-2]");
        TreeTextVisualizer visualizer = new TreeTextVisualizer(treeNode);
        visualizer.setPreferredSize(new Dimension(800, 1200));
        JFrameShow jFrameShow = new JFrameShow(visualizer);
        jFrameShow.show();

        // 开启gif记录
        visualizer.startGif("FibonacciIssue_v2.gif");
        Utils.sleepMilliSeconds(200L);

        long result = fibonacci_v2_view(n, dict, visualizer, treeNode);

        // 完成gif记录
        visualizer.finishGif();
        // jFrameShow.close();

        return result;
    }

    // 简单递归-可视化版本(效率较低)
    public static long fibonacci_v1_view(int n, TreeTextVisualizer visualizer, TreeNode treeNode) {
        if ((n == 0) || (n == 1)) {
            addTextSHow(visualizer, treeNode, String.format("F[%s]=%s", n, n));
            return n;
        }

        addTextSHow(visualizer, treeNode, String.format("F[%s]=F[%s]+F[%s]=?", n, (n - 2), (n - 1)));

        TreeNode treeNodeB = new TreeNode("");
        TreeNode treeNodeA = new TreeNode("");
        treeNode.addChild(treeNodeB).addChild(treeNodeA);

        long b = fibonacci_v1_view(n - 2, visualizer, treeNodeB);
        long a = fibonacci_v1_view(n - 1, visualizer, treeNodeA);

        addTextSHow(visualizer, treeNode, String.format("F[%s]=F[%s]+F[%s]=%s+%s=%s", n, (n - 2), (n - 1), b, a, (a+b)));
        
        return a + b;
    }

    private static void addTextSHow(TreeTextVisualizer visualizer, TreeNode treeNode, String text) {
        treeNode.setName(text);
        visualizer.refreshShow();
        Utils.sleepMilliSeconds(10L);
    }

    // 带记忆的递归
    public static long fibonacci_v2(int n,long[] dict) {
        if ((n == 0) || (n == 1)) {
            dict[n] = n;
            return n;
        }
        if (dict[n] != 0) return dict[n];

        long a = fibonacci_v2(n - 1, dict);
        long b = fibonacci_v2(n - 2, dict);

        return dict[n] = a + b;
    }

    // 带记忆的递归(动画版本)
    public static long fibonacci_v2_view(int n,long[] dict, TreeTextVisualizer visualizer, TreeNode treeNode) {
        if ((n == 0) || (n == 1)) {
            dict[n] = n;
            addTextSHow(visualizer, treeNode, String.format("F[%s]=%s", n, n));
            return n;
        }
        if (dict[n] != 0) {
            // 计算过就不再展示来
            treeNode.getParent().getTreeNodes().remove(treeNode);
            return dict[n];
        }

        addTextSHow(visualizer, treeNode, String.format("F[%s]=F[%s]+F[%s]=?", n, (n - 2), (n - 1)));

        TreeNode treeNodeB = new TreeNode("");
        TreeNode treeNodeA = new TreeNode("");
        treeNode.addChild(treeNodeB).addChild(treeNodeA);

        long b = fibonacci_v2_view(n - 2, dict, visualizer, treeNodeB);
        long a = fibonacci_v2_view(n - 1, dict, visualizer, treeNodeA);

        addTextSHow(visualizer, treeNode, String.format("F[%s]=F[%s]+F[%s]=%s+%s=%s", n, (n - 2), (n - 1), b, a, (a+b)));

        return dict[n] = a + b;
    }


    /**
     * 动态规划，递推求解
     * 类似问题 @see com.tc.alg.derivation.StairAlgIssue
     */
    public static long fibonacci_v3(int n) {
        if ((n == 0) || (n == 1))
            return n;
        // 创建一个数组来存储每个台阶的方法数(最好使用long来进行存储，int很快就要超长)
        long[] dp = new long[n + 1];
        dp[0] = 0;
        dp[1] = 1;

        // 使用循环填充数组，根据递推关系dp[i] = dp[i-1] + dp[i-2]来计算到达每个台阶的方法数
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }
}
