package com.example.nasadict.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LinkDTO {
    @SerializedName("href")
    @Expose
    private String href;

    public void setHref(String href) {
        this.href = href;
    }

    public String getHref() {
        return href;
    }
}
