package com.rferl.SWEN.model;

public class Relation {

    private String urlCurrent;

    private String urlOther;

    private boolean relation;

    public String getUrlCurrent() {
        return urlCurrent;
    }

    public void setUrlCurrent(String urlCurrent) {
        this.urlCurrent = urlCurrent;
    }

    public String getUrlOther() {
        return urlOther;
    }

    public void setUrlOther(String urlOther) {
        this.urlOther = urlOther;
    }

    public boolean isRelation() {
        return relation;
    }

    public void setRelation(boolean relation) {
        this.relation = relation;
    }

}
