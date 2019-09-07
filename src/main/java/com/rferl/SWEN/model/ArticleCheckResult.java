package com.rferl.SWEN.model;


import java.util.List;

public class ArticleCheckResult {
    private CheckList checkList;
    private List<TitleAndUrl> positives;
    private List<TitleAndUrl> negatives;
    private List<String> owners;

    public ArticleCheckResult(CheckList checkList, List<TitleAndUrl> positives, List<TitleAndUrl> negatives, List<String> owners) {
        this.checkList = checkList;
        this.positives = positives;
        this.negatives = negatives;
        this.owners = owners;
    }

    public CheckList getCheckList() {
        return checkList;
    }

    public void setCheckList(CheckList checkList) {
        this.checkList = checkList;
    }

    public List<TitleAndUrl> getPositives() {
        return positives;
    }

    public void setPositives(List<TitleAndUrl> positives) {
        this.positives = positives;
    }

    public List<TitleAndUrl> getNegatives() {
        return negatives;
    }

    public void setNegatives(List<TitleAndUrl> negatives) {
        this.negatives = negatives;
    }

    public List<String> getOwners() {
        return owners;
    }

    public void setOwners(List<String> owners) {
        this.owners = owners;
    }
}
