package com.example.myapplication;

import com.google.gson.annotations.SerializedName;
import com.google.gson.annotations.SerializedName;

public class AnimeResponse {
    @SerializedName("data")
    private AnimeData data;

    public AnimeData getData() {
        return data;
    }
}

class AnimeData {
    @SerializedName("title")
    private String title;

    public String getTitle() {
        return title;
    }
}
