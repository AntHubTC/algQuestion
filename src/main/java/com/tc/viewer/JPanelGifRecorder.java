package com.tc.viewer;

import cn.hutool.core.img.gif.AnimatedGifEncoder;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * JPanel面板GIF录制器
 */
public class JPanelGifRecorder {
    /**
     * Gif记录器
     */
    private AnimatedGifEncoder animatedGifEncoder;
    /**
     * 被记录的JPanel面板
     */
    private final JPanel targetJPanel;

    public JPanelGifRecorder(JPanel jPanel) {
        this.targetJPanel = jPanel;
    }

    /**
     * 开始录制gif
     */
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


    /**
     * 给JPanel拍一张照片
     */
    public void takePicture() {
        if (null != animatedGifEncoder) {
            int width = this.targetJPanel.getWidth();
            int height = this.targetJPanel.getHeight();
            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

            Graphics2D imageG = image.createGraphics();
            this.targetJPanel.paint(imageG);
            imageG.dispose();

            animatedGifEncoder.addFrame(image);
        }
    }

    /**
     * 完成gif录制
     */
    public void finishGif() {
        if (null != animatedGifEncoder) {
            animatedGifEncoder.finish();
        }
    }

}
