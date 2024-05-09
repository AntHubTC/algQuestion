package com.tc.alg.dfs;

import com.tc.utl.Utils;
import com.tc.viewer.GridVisualizer;
import com.tc.viewer.JFrameShow;

public class MaximumInfectionArea {
    public static void main(String[] args) {
        // 问题：给一个感染矩阵，1代表感染，0代表没感染，求最大感染区的面积
        // int[][] grid = { // 给定的感染矩阵
        //         {1, 1, 0, 0, 1},
        //         {1, 0, 0, 1, 0},
        //         {1, 0, 0, 1, 0},
        //         {0, 1, 1, 1, 1},
        // };
        int[][] grid = { // 给定的感染矩阵
                {1, 1, 0, 0, 1, 0, 1},
                {1, 0, 0, 1, 0, 1, 0},
                {1, 0, 0, 1, 0, 1, 0},
                {0, 1, 1, 1, 1, 1, 1},
                {0, 0, 1, 1, 1, 1, 1},
                {0, 0, 1, 1, 1, 1, 1},
                {1, 0, 1, 0, 0, 0, 0}
        };


        // 算法格子可视化
        GridVisualizer visualizer = new GridVisualizer(grid);
        JFrameShow jFrameShow = new JFrameShow(visualizer);
        jFrameShow.show();

        // 开启gif记录
        visualizer.startGif("MaximumInfectionArea.gif");
        Utils.sleepMilliSeconds(200L);

        // 输出最大感染区的面积
        int maxInfectionArea = maxInfectionArea(grid, visualizer);
        System.out.println("最大感染区的面积为: " + maxInfectionArea);

        // 完成gif记录
        visualizer.finishGif();
        //jFrameShow.close();
    }

    // 走过的感染区域
    public static final int FLAG_OVER = 2;
    // 感染区域
    public static final int FLAG_YES = 1;
    // 未感染区域
    public static final int FLAG_NO = 0;

    private static int maxInfectionArea(int[][] grid, GridVisualizer visualizer) {
        int maxArea = 0;

        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                if (grid[row][col] == FLAG_YES) {
                    int area = dfs(grid, row, col, visualizer);
                    System.out.println(area);
                    maxArea = Math.max(maxArea, area);
                }
            }
        }

        return maxArea;
    }

    private static int dfs(int[][] grid, int row, int col, GridVisualizer visualizer) {
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[row].length || grid[row][col] == FLAG_NO || grid[row][col] == 2) {
            return 0;
        }

        Utils.sleepSeconds(1L);
        grid[row][col] = FLAG_OVER; // 走过的位置，标记为未感染
        visualizer.refreshShow();

        int area = 1;
        area += dfs(grid, row, col - 1, visualizer);
        area += dfs(grid, row, col + 1, visualizer);
        area += dfs(grid, row - 1, col, visualizer);
        area += dfs(grid, row + 1, col, visualizer);
        return area;
    }

}
