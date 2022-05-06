package com.example.nasadict.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CollectionDTO {
    @SerializedName("items")
    @Expose
    private ArrayList<SingleItemDTO> items;

    public void setItems(ArrayList<SingleItemDTO> items) {
        this.items = items;
    }

    public ArrayList<SingleItemDTO> getItems() {
        return items;
    }
}
