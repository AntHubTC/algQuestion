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

    /**
     * 计算网格中最大感染区域的面积。
     * @param grid 表示网格的二维数组，其中元素代表网格的各个单元格的状态。
     * @param visualizer GridVisualizer对象，用于可视化网格，不是必需的，可以为空。
     * @return 返回最大的感染区域面积。
     */
    private static int maxInfectionArea(int[][] grid, GridVisualizer visualizer) {
        int maxArea = 0; // 初始化最大面积为0

        // 双重循环遍历网格
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                // 如果单元格被感染（FLAG_YES）
                if (grid[row][col] == FLAG_YES) {
                    // 使用深度优先搜索计算当前感染区域的面积
                    int area = dfs(grid, row, col, visualizer);
                    System.out.println(area); // 打印当前感染区域面积，用于调试或可视化
                    maxArea = Math.max(maxArea, area); // 更新最大面积
                }
            }
        }

        return maxArea; // 返回最大面积
    }

    /**
     * 使用深度优先搜索（DFS）算法遍历网格并计算感染区域的大小。
     * @param grid 表示感染区域的二维网格。
     * @param row 当前遍历的行索引。
     * @param col 当前遍历的列索引。
     * @param visualizer 用于可视化网格状态的对象。
     * @return 返回感染区域的大小。
     */
    private static int dfs(int[][] grid, int row, int col, GridVisualizer visualizer) {
        // 检查当前位置是否越界或已被标记，若是则返回0
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[row].length || grid[row][col] == FLAG_NO || grid[row][col] == 2) {
            return 0;
        }

        Utils.sleepSeconds(1L); // 暂停1秒，增加可视化效果
        grid[row][col] = FLAG_OVER; // 将当前位置标记为已访问（未感染）
        visualizer.refreshShow(); // 刷新显示，更新可视化效果

        int area = 1; // 当前感染区域的大小
        // 递归遍历四个方向的相邻位置
        area += dfs(grid, row, col - 1, visualizer);
        area += dfs(grid, row, col + 1, visualizer);
        area += dfs(grid, row - 1, col, visualizer);
        area += dfs(grid, row + 1, col, visualizer);
        return area;
    }

}
