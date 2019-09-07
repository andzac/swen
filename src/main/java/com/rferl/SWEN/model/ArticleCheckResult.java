package com.rferl.SWEN.model;


import java.util.List;

public class ArticleCheckResult {
    private CheckList checkList;
    private List<String> positives;
    private List<String> negatives;

    public ArticleCheckResult(CheckList checkList, List<String> positives, List<String> negatives) {
        this.checkList = checkList;
        this.positives = positives;
        this.negatives = negatives;
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

}
