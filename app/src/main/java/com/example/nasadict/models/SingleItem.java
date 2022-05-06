package com.example.nasadict.models;

public class SingleItem {
    private String image;
    private String title;
    private String description;
    private String date_created;

    public SingleItem(String href, String title, String description, String date_created) {
        this.image = href;
        this.title = title;
        this.description = description;
        this.date_created = date_created;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate_created(String date_created) {
        this.date_created = date_created;
    }

    public String getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDate_created() {
        return date_created;
    }
}
