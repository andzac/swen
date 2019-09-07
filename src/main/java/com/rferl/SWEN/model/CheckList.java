package com.rferl.SWEN.model;

public class CheckList {
    private boolean isInBlackList;
    private boolean isOnFirstApril;
    private boolean isMoreThanOneWeekOld;
    private boolean hasCapsOverusage;

    public CheckList(boolean isInBlackList, boolean isOnFirstApril) {
        this.isInBlackList = isInBlackList;
        this.isOnFirstApril = isOnFirstApril;
    }


    public boolean isOnFirstApril() {
        return isOnFirstApril;
    }

    public void setOnFirstApril(boolean onFirstApril) {
        isOnFirstApril = onFirstApril;
    }

    public boolean isMoreThanOneWeekOld() {
        return isMoreThanOneWeekOld;
    }

    public void setMoreThanOneWeekOld(boolean moreThanOneWeekOld) {
        isMoreThanOneWeekOld = moreThanOneWeekOld;
    }

    public boolean isHasCapsOverusage() {
        return hasCapsOverusage;
    }

    public void setHasCapsOverusage(boolean hasCapsOverusage) {
        this.hasCapsOverusage = hasCapsOverusage;
    }

    public boolean isInBlackList() {
        return isInBlackList;
    }

    public void setInBlackList(boolean inBlackList) {
        this.isInBlackList = inBlackList;
    }
}
