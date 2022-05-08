package com.example.nasadict.models;

import android.os.Parcel;
import android.os.Parcelable;

public class SingleItem implements Parcelable {
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

    public SingleItem() {
    }

    protected SingleItem(Parcel in) {
        title = in.readString();
        description = in.readString();
        date_created = in.readString();
        image = in.readString();
    }

    public static final Creator<SingleItem> CREATOR = new Creator<SingleItem>() {
        @Override
        public SingleItem createFromParcel(Parcel in) {
            return new SingleItem(in);
        }

        @Override
        public SingleItem[] newArray(int size) {
            return new SingleItem[size];
        }
    };

    @Override
    public int describeContents() {
        return hashCode();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(image);
        parcel.writeString(title);
        parcel.writeString(description);
        parcel.writeString(date_created);
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
