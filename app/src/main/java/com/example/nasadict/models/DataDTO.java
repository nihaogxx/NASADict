package com.example.nasadict.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataDTO {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("date_created")
    @Expose
    private String date_created;


    public void setTitle(String title) {
        this.title = title;
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

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate_created(String date_created) {
        this.date_created = date_created;
    }
}


