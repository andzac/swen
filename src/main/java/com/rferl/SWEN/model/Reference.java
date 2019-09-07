package com.rferl.SWEN.model;

public class Reference {
    private String url1;
    private String url2;
    private boolean type;
    private double similarity;

    public Reference(String url1, String url2, boolean type, double similarity) {
        this.url1 = url1;
        this.url2 = url2;
        this.type = type;
        this.similarity = similarity;
    }

    public String getUrl1() {
        return url1;
    }

    public void setUrl1(String url1) {
        this.url1 = url1;
    }

    public String getUrl2() {
        return url2;
    }

    public void setUrl2(String url2) {
        this.url2 = url2;
    }

    public boolean isType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }

    public double getSimilarity() {
        return similarity;
    }

    public void setSimilarity(double similarity) {
        this.similarity = similarity;
    }
}
