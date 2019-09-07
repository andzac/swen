package com.rferl.SWEN.model;

public class Reference {
    private int id;
    private String url1;
    private String url2;
    private String fullUrl;
    private boolean type;
    private double similarity;

    public Reference(String url1, String url2, String fullUrl, boolean type, double similarity) {
        this.url1 = url1;
        this.url2 = url2;
        this.fullUrl = fullUrl;
        this.type = type;
        this.similarity = similarity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getFullUrl() {
        return fullUrl;
    }

    public void setFullUrl(String fullUrl) {
        this.fullUrl = fullUrl;
    }
}
