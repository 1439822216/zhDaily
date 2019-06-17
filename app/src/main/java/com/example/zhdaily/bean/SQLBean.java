package com.example.zhdaily.bean;

public class SQLBean {
    private String newId;
    private String title;
    private String image;
    private String timedata;

    public String getNewId() {
        return newId;
    }

    public void setNewId(String newId) {
        this.newId = newId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTimedata() {
        return timedata;
    }

    public void setTimedata(String timedata) {
        this.timedata = timedata;
    }

    public SQLBean(String newId, String title, String image, String timedata) {
        this.newId = newId;
        this.title = title;
        this.image = image;
        this.timedata = timedata;
    }

    public SQLBean() {
    }
}
