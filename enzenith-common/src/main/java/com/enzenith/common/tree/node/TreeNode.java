package com.enzenith.common.tree.node;

import java.util.ArrayList;
import java.util.List;

/**
 * 树形节点
 * @title: TreeNode
 * @description: TODO
 * @author jikunle
 * @date 2019/3/6 1:07
 */
public class TreeNode{
    //树节点ID
    private String id;
    //树节点名称
    private String name;
    //父节点ID
    private String pid;
    //是否选中
    private String checked;
    //是否可编辑
    private String editable;
 //   private int level;
//    private TreeNode parent;
    //当前节点的二子节点

    private List<TreeNode> children = new ArrayList<TreeNode>();

    private TreeNode treeNode;
    //当前节点的子孙节点
//    private List<TreeNode> allChildren = new ArrayList<TreeNode>();

    public TreeNode(TreeNode obj){
        this.id = obj.getId();
        this.name = obj.getName();
        this.pid = obj.getPid();
        this.checked = obj.getChecked();
        this.editable = obj.getEditable();
        this.treeNode = obj;
    }
    public TreeNode() {
    }

    public void addChild(TreeNode treeNode){
        this.children.add(treeNode);
    }
    public void removeChild(TreeNode treeNode){
        this.children.remove(treeNode);
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPid() {
        return pid;
    }
    public void setPid(String pid) {
        this.pid = pid;
    }
/*    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
    }*/
/*    public TreeNode getParent() {
        return parent;
    }
    public void setParent(TreeNode parent) {
        this.parent = parent;
    }*/
    public List<TreeNode> getChildren() {
        return children;
    }
    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }

    public String getChecked() {
        return checked;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }

    public String getEditable() {
        return editable;
    }

    public void setEditable(String editable) {
        this.editable = editable;
    }

    public TreeNode getTreeNode() {
        return treeNode;
    }

    public void setTreeNode(TreeNode treeNode) {
        this.treeNode = treeNode;
    }

/*public List<TreeNode> getAllChildren() {
        if(this.allChildren.isEmpty()){
            for(TreeNode treeNode : this.children){
                this.allChildren.add(treeNode);
                this.allChildren.addAll(treeNode.getAllChildren());
            }
        }
        return this.allChildren;
    }*/
}
