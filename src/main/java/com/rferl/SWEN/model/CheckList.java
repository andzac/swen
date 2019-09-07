package com.rferl.SWEN.model;

public class CheckList {
    private boolean isInBlackList;

    public CheckList(boolean isInBlackList) {
        this.isInBlackList = isInBlackList;
    }

    public boolean isInBlackList() {
        return isInBlackList;
    }

    public void setInBlackList(boolean inBlackList) {
        this.isInBlackList = inBlackList;
    }
}
