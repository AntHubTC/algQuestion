package com.tc.viewer;

import cn.hutool.core.img.gif.AnimatedGifEncoder;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * 用于显示int[][] grid的数据
 *
 * @author tangcheng_cd
 * @version 1.0
 * @className FF
 * @description
 * @date 2024/5/8 12:21
 **/
public class GridVisualizer extends JPanel {
    private static final int CELL_SIZE = 30;
    private static final Color INFECTED_COLOR = Color.RED;
    private static final Color OVER_COLOR = Color.GREEN;
    private static final Color UNINFECTED_COLOR = Color.WHITE;
    private int[][] grid;
    private AnimatedGifEncoder animatedGifEncoder;

    public GridVisualizer(int[][] grid) {
        this.grid = grid;
        setPreferredSize(new Dimension(grid[0].length * CELL_SIZE, grid.length * CELL_SIZE));
    }

    public void startGif(String fileName) {
        if (null != animatedGifEncoder) {
            return;
        }
        animatedGifEncoder = new AnimatedGifEncoder();
        animatedGifEncoder.start(fileName);
        animatedGifEncoder.setQuality(1);
        animatedGifEncoder.setDelay(800);
        animatedGifEncoder.setRepeat(0);
    }

    public void finishGif() {
        if (null != animatedGifEncoder) {
            animatedGifEncoder.finish();
        }
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

    public void updateGrid(int[][] grid) {
        this.grid = grid;
        repaint();

        if (null != animatedGifEncoder) {
            BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
            Graphics2D imageG = image.createGraphics();
            paint(imageG);
            imageG.dispose();
            animatedGifEncoder.addFrame(image);
        }
    }
}