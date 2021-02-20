package com.smallcompany.nice.model;

public class Peolpletype {
    private Integer ptId;

    private Integer ptSort;

    private String ptName;

    private Integer ptStatus;

    public Integer getPtId() {
        return ptId;
    }

    public void setPtId(Integer ptId) {
        this.ptId = ptId;
    }

    public Integer getPtSort() {
        return ptSort;
    }

    public void setPtSort(Integer ptSort) {
        this.ptSort = ptSort;
    }

    public String getPtName() {
        return ptName;
    }

    public void setPtName(String ptName) {
        this.ptName = ptName == null ? null : ptName.trim();
    }

    public Integer getPtStatus() {
        return ptStatus;
    }

    public void setPtStatus(Integer ptStatus) {
        this.ptStatus = ptStatus;
    }
}