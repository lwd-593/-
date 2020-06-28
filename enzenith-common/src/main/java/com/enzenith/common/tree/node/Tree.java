package com.enzenith.common.tree.node;

import java.util.*;

/**
 * 树
 * @title: Tree
 * @description: TODO
 * @author jikunle
 * @date 2019/3/6 1:10
 */
public class Tree implements ITree {
    //treeNode加入有序map,保证正常排序
    //为了保证有序，需将treeNodesMap设置为LinkedHashMap类型
    private HashMap<String, TreeNode> treeNodesMap = new LinkedHashMap<>();
    private List<TreeNode> treeNodesList = new ArrayList<TreeNode>();

    public Tree(List<TreeNode> list){
        initTreeNodeMap(list);
        initTreeNodeList();
    }


    /**
     * 初始化节点列表
     * @param list
     * @return void
     * @author jikunle
     * @date 2019/3/6 17:04
     */
    private void initTreeNodeMap(List<TreeNode> list){
        TreeNode treeNode = null;
        for(TreeNode item : list){
            treeNode = new TreeNode(item);
            treeNodesMap.put(treeNode.getId(), treeNode);
        }

        Iterator<TreeNode> iter = treeNodesMap.values().iterator();
        TreeNode parentTreeNode = null;
        while(iter.hasNext()){
            treeNode = iter.next();
            // 根节点的父级节点id为null
            if(treeNode.getPid() == null || treeNode.getPid() == ""){
                continue;
            }

            parentTreeNode = treeNodesMap.get(treeNode.getPid());
            if(parentTreeNode != null){
             //   treeNode.setParent(parentTreeNode);
                parentTreeNode.addChild(treeNode);
            }
        }
    }

    private void initTreeNodeList(){
        if(treeNodesList.size() > 0){
            return;
        }
        if(treeNodesMap.size() == 0){
            return;
        }
        Iterator<TreeNode> iter = treeNodesMap.values().iterator();
        TreeNode treeNode = null;
        while(iter.hasNext()){
            treeNode = iter.next();
            if(treeNode.getPid() == null){
                this.treeNodesList.add(treeNode);
           //     this.treeNodesList.addAll(treeNode.getAllChildren());
            }else{
                TreeNode parentTreeNode = treeNodesMap.get(treeNode.getPid());
                /**
                 * 新增上级节点不存在时，作为根节点加入list
                 * jikunle
                 * 2019-03-23
                 */
                if(parentTreeNode == null){
                    this.treeNodesList.add(treeNode);
                }
            }
        }
    }

    @Override
    public List<TreeNode> getTree() {
        return this.treeNodesList;
    }

    @Override
    public List<TreeNode> getRoot() {
        List<TreeNode> rootList = new ArrayList<TreeNode>();
        if (this.treeNodesList.size() > 0) {
            for (TreeNode node : treeNodesList) {
                if (node.getPid() == null) {
                    rootList.add(node);
                }
            }
        }
        return rootList;
    }

    @Override
    public TreeNode getTreeNode(String nodeId) {
        return this.treeNodesMap.get(nodeId);
    }

    /**
     * 将扁平对象列表转换成树形对象
     * @param srcList   需要转换的对象列表
     * @return java.util.List<TreeNode>
     * @author jikunle
     * @date 2019/3/7 17:21
     */
    public static List<TreeNode> convertToTreeList(List srcList) {
        Tree tree = new Tree(srcList);
        /**
         * 从tree.getRoot()调整为tree.treeNodesList，从而可以获取根节点pid不是null的树
         * jikunle
         * 2019-03-23
         */
        List<TreeNode> rootList = tree.treeNodesList;
        return rootList;
    }
}
