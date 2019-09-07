package com.rferl.SWEN.model;

public class CheckList {
    private boolean isInBlackList;
    private boolean isOnFirstApril;
    private boolean isMoreThanOneWeekOld;
    private boolean containsTextLinks;
    private boolean isAuthorPresent;
    private boolean isJokeSite;

    public CheckList(boolean isInBlackList,
                     boolean isOnFirstApril,
                     boolean isMoreThanOneWeekOld,
                     boolean containsTextLinks,
                     boolean isAuthorPresent,
                     boolean isJokeSite) {
        this.isInBlackList = isInBlackList;
        this.isOnFirstApril = isOnFirstApril;
        this.isMoreThanOneWeekOld = isMoreThanOneWeekOld;
        this.containsTextLinks = containsTextLinks;
        this.isAuthorPresent = isAuthorPresent;
        this.isJokeSite = isJokeSite;
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

    public boolean isAuthorPresent() {
        return isAuthorPresent;
    }

    public void setAuthorPresent(boolean authorPresent) {
        isAuthorPresent = authorPresent;
    }

    public boolean isJokeSite() {
        return isJokeSite;
    }

    public void setJokeSite(boolean jokeSite) {
        isJokeSite = jokeSite;
    }
}
