package com.rferl.SWEN.model;

public class TitleAndUrl {
    private String title;
    private String url;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public TitleAndUrl(String title, String url) {
        this.title = title;
        this.url = url;
    }
}
