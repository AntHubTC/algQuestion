package com.tc.datastruct;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class TreeNode {
    /**
     * 树节点id
     */
    private Integer id;
    /**
     * 树节点名称
     */
    private String name;
    /**
     * 数的级别
     */
    private Integer level = 1;
    /**
     * 父节点
     */
    private TreeNode parent;
    /**
     * 树子节点
     */
    private List<TreeNode> treeNodes;

    public TreeNode(String name) {
        this.name = name;
        this.treeNodes = new ArrayList<>();
    }

    public TreeNode(Integer id, String name) {
        this(name);
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TreeNode addChild(TreeNode treeNode) {
        treeNode.setLevel(getLevel()+1);
        treeNode.setParent(this);
        this.treeNodes.add(treeNode);
        return this;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public List<TreeNode> getTreeNodes() {
        return treeNodes;
    }

    public void setTreeNodes(List<TreeNode> treeNodes) {
        this.treeNodes = treeNodes;
    }

    public TreeNode getParent() {
        return parent;
    }

    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TreeNode)) return false;
        TreeNode treeNode = (TreeNode) o;
        return Objects.equals(id, treeNode.id) && Objects.equals(name, treeNode.name) && Objects.equals(treeNodes, treeNode.treeNodes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, treeNodes);
    }
}
