package com.example.nasadict.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Collection;

public class ResponseDTO {
    @SerializedName("collection")
    @Expose
    private CollectionDTO collection;

    public void setCollection(CollectionDTO collection) {
        this.collection = collection;
    }

    public CollectionDTO getCollection() {
        return collection;
    }


}
