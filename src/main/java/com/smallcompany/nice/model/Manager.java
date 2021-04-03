package com.smallcompany.nice.model;

public class Manager {
    private Integer mngId;

    private String mngName;

    private String mngNumber;

    private Integer mngStatus;

    private String mngHeadshot;

    private String mngTel;

    private String mngPwd;

    private String atPower;

    private String atName;

    public String getAtPower() {
        return atPower;
    }

    public void setAtPower(String atPower) {
        this.atPower = atPower;
    }

    public String getAtName() {
        return atName;
    }

    public void setAtName(String atName) {
        this.atName = atName;
    }

    public Integer getMngId() {
        return mngId;
    }

    public void setMngId(Integer mngId) {
        this.mngId = mngId;
    }

    public String getMngName() {
        return mngName;
    }

    public void setMngName(String mngName) {
        this.mngName = mngName == null ? null : mngName.trim();
    }

    public String getMngNumber() {
        return mngNumber;
    }

    public void setMngNumber(String mngNumber) {
        this.mngNumber = mngNumber == null ? null : mngNumber.trim();
    }

    public Integer getMngStatus() {
        return mngStatus;
    }

    public void setMngStatus(Integer mngStatus) {
        this.mngStatus = mngStatus;
    }

    public String getMngHeadshot() {
        return mngHeadshot;
    }

    public void setMngHeadshot(String mngHeadshot) {
        this.mngHeadshot = mngHeadshot == null ? null : mngHeadshot.trim();
    }

    public String getMngTel() {
        return mngTel;
    }

    public void setMngTel(String mngTel) {
        this.mngTel = mngTel;
    }

    public String getMngPwd() {
        return mngPwd;
    }

    public void setMngPwd(String mngPwd) {
        this.mngPwd = mngPwd == null ? null : mngPwd.trim();
    }
}