package com.smallcompany.nice.model;

import java.util.Date;

public class People {
    private Integer ppId;

    private String ppName;

    private String ppPosition;

    private Integer ppSort;

    private Date ppEdtime;

    public Integer getPpId() {
        return ppId;
    }

    public void setPpId(Integer ppId) {
        this.ppId = ppId;
    }

    public String getPpName() {
        return ppName;
    }

    public void setPpName(String ppName) {
        this.ppName = ppName == null ? null : ppName.trim();
    }

    public String getPpPosition() {
        return ppPosition;
    }

    public void setPpPosition(String ppPosition) {
        this.ppPosition = ppPosition == null ? null : ppPosition.trim();
    }

    public Integer getPpSort() {
        return ppSort;
    }

    public void setPpSort(Integer ppSort) {
        this.ppSort = ppSort;
    }

    public Date getPpEdtime() {
        return ppEdtime;
    }

    public void setPpEdtime(Date ppEdtime) {
        this.ppEdtime = ppEdtime;
    }
}