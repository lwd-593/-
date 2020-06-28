package com.enzenith.common;

import com.enzenith.common.tree.node.TreeNode;

/**
 * @author jikunle
 * @title: Org
 * @description: TODO
 * @date 2019/3/6 1:17
 */
public class Org extends TreeNode {
    public Org(TreeNode obj) {
        super(obj);
    }
    private String uuid;
    private String parentId;
    private String name;
    private Integer orderNum;
    private String code;
    private String type;

    //是否选中
    private String checked;
    //是否可编辑
    private String editable;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String getChecked() {
        return checked;
    }

    @Override
    public void setChecked(String checked) {
        this.checked = checked;
    }

    @Override
    public String getEditable() {
        return editable;
    }

    @Override
    public void setEditable(String editable) {
        this.editable = editable;
    }



}
