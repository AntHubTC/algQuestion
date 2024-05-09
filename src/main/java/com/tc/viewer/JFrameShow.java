package com.tc.viewer;

import javax.swing.*;

public class JFrameShow {
    private JFrame frame;

    public JFrameShow(JPanel jPanel) {
        JFrame frame = new JFrame("Data Visualizer");
        frame.getContentPane().add(jPanel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        this.frame = frame;
    }

    public void show() {
        frame.setVisible(true);
    }


    public void close() {
        // frame.setVisible(false);
        frame.dispose();
    }
}
