package com.example.nasadict.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class SingleItemDTO {

    @SerializedName("data")
    @Expose
    private ArrayList<DataDTO> data;

    @SerializedName("links")
    @Expose
    private ArrayList<LinkDTO> links;

    public ArrayList<DataDTO> getData() {
        return data;
    }

    public ArrayList<LinkDTO> getLinks() {
        return links;
    }

    public void setData(ArrayList<DataDTO> data) {
        this.data = data;
    }

    public void setLinks(ArrayList<LinkDTO> links) {
        this.links = links;
    }


}
