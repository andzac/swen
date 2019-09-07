package com.rferl.SWEN.model;

public class CheckList {
    private boolean isInBlackList;
    private boolean isOnFirstApril;
    private boolean isMoreThanOneWeekOld;
    private boolean hasCapsOverusage;
    private boolean containsTextLinks;
    private boolean areImagesLinksAvailable;
    private boolean isAuthorPresent;


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

    public boolean isContainsTextLinks() {
        return containsTextLinks;
    }

    public void setContainsTextLinks(boolean containsTextLinks) {
        this.containsTextLinks = containsTextLinks;
    }

    public boolean isAreImagesLinksAvailable() {
        return areImagesLinksAvailable;
    }

    public void setAreImagesLinksAvailable(boolean areImagesLinksAvailable) {
        this.areImagesLinksAvailable = areImagesLinksAvailable;
    }

    public boolean isAuthorPresent() {
        return isAuthorPresent;
    }

    public void setAuthorPresent(boolean authorPresent) {
        isAuthorPresent = authorPresent;
    }
}
