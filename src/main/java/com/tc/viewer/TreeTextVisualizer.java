package com.tc.viewer;

import com.tc.datastruct.TreeNode;

import java.awt.*;
import java.util.Collections;
import java.util.List;

/**
 * 用于显示树形数据
 */
public class TreeTextVisualizer extends GifRecorderJPanel {
    private TreeNode treeNode;

    public TreeTextVisualizer(TreeNode treeNode) {
        this.treeNode = treeNode;

        setPreferredSize(new Dimension(800, 800));
    }

    @Override
    protected void paintComponent(Graphics g) {
        // g.clearRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
        paintTree(Collections.singletonList(treeNode), 1, g, 10);
    }

    private void paintTree(List<TreeNode> treeNodes, int level, Graphics g, int startY) {
        for (TreeNode node : treeNodes) {
            startY += 50;

            g.setColor(Color.RED);

            Font font = new Font("Arial", Font.BOLD, 24);
            g.setFont(font);
            g.drawString(node.getName(), 35 * level, startY);

            paintTree(node.getTreeNodes(), level + 1, g, startY);
        }
    }

    public TreeNode getTreeNode() {
        return treeNode;
    }
}
