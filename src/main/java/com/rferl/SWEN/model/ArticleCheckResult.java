package com.rferl.SWEN.model;


import java.util.List;

public class ArticleCheckResult {
    private CheckList checkList;
    private List<String> positives;
    private List<String> negatives;
    private List<String> owners;

    public ArticleCheckResult(CheckList checkList, List<String> positives, List<String> negatives, List<String> owners) {
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

    public List<String> getPositives() {
        return positives;
    }

    public void setPositives(List<String> positives) {
        this.positives = positives;
    }

    public List<String> getNegatives() {
        return negatives;
    }

    public void setNegatives(List<String> negatives) {
        this.negatives = negatives;
    }

    public List<String> getOwners() {
        return owners;
    }

    public void setOwners(List<String> owners) {
        this.owners = owners;
    }
}
