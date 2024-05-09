package com.tc.viewer;

import javax.swing.*;
import java.awt.*;

/**
 * 用于显示int[][] grid的数据
 *
 * @author tangcheng_cd
 * @version 1.0
 * @className FF
 * @description
 * @date 2024/5/8 12:21
 **/
public class GridVisualizer extends GifRecorderJPanel {
    private static final int CELL_SIZE = 30;
    private static final Color INFECTED_COLOR = Color.RED;
    private static final Color OVER_COLOR = Color.GREEN;
    private static final Color UNINFECTED_COLOR = Color.WHITE;
    private int[][] grid;

    public GridVisualizer(int[][] grid) {
        this.grid = grid;
        setPreferredSize(new Dimension(grid[0].length * CELL_SIZE, grid.length * CELL_SIZE));
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                Color color = (grid[i][j] == 1) ? INFECTED_COLOR : UNINFECTED_COLOR;
                color = (grid[i][j] == 2) ? OVER_COLOR : color;

                g.setColor(color);
                g.fillRect(j * CELL_SIZE, i * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                g.setColor(Color.BLACK);
                g.drawRect(j * CELL_SIZE, i * CELL_SIZE, CELL_SIZE, CELL_SIZE);
            }
        }
    }
}