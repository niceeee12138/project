package com.smallcompany.nice.model;

public class Authoritytype {
    private Integer atId;

    private String atPower;

    private String atName;

    private Integer atSort;

    private Integer atEdit;

    private Integer atAdd;

    public Integer getAtId() {
        return atId;
    }

    public void setAtId(Integer atId) {
        this.atId = atId;
    }

    public String getAtPower() {
        return atPower;
    }

    public void setAtPower(String atPower) {
        this.atPower = atPower == null ? null : atPower.trim();
    }

    public String getAtName() {
        return atName;
    }

    public void setAtName(String atName) {
        this.atName = atName == null ? null : atName.trim();
    }

    public Integer getAtSort() {
        return atSort;
    }

    public void setAtSort(Integer atSort) {
        this.atSort = atSort;
    }

    public Integer getAtEdit() {
        return atEdit;
    }

    public void setAtEdit(Integer atEdit) {
        this.atEdit = atEdit;
    }

    public Integer getAtAdd() {
        return atAdd;
    }

    public void setAtAdd(Integer atAdd) {
        this.atAdd = atAdd;
    }
}