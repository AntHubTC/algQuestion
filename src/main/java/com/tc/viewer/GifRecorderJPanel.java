package com.tc.viewer;

import com.tc.utl.Utils;

import javax.swing.*;
import java.awt.*;

public class GifRecorderJPanel extends JPanel {
    private JPanelGifRecorder jPanelGifRecorder;

    public GifRecorderJPanel() {
        jPanelGifRecorder = new JPanelGifRecorder(this);
    }

    public void startGif(String fileName) {
        jPanelGifRecorder.startGif(Utils.getOutImgPath() + fileName);
    }


    public void refreshShow() {
        // 重新绘制
        repaint();
        // 拍一张照片
        jPanelGifRecorder.takePicture();
    }

    public void finishGif() {
        // 结束故意加几个相同帧
        jPanelGifRecorder.takePicture();
        jPanelGifRecorder.takePicture();
        jPanelGifRecorder.takePicture();

        jPanelGifRecorder.finishGif();
    }
}
