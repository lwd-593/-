package com.enzenith.common.tree.node;

import java.util.List;

/**
 * 树接口
 * @title: ITree
 * @description: TODO
 * @author jikunle
 * @date 2019/3/6 1:09
 */
public interface ITree {
    List<TreeNode> getTree();
    List<TreeNode> getRoot();
    TreeNode getTreeNode(String nodeId);
}
