package com.example.yohan.recycleviewjson;

public class items {

    private String mimgeUrl;
    private String mCreator;
    private int mLikes;

    public items(String imgeUrl, String mCreator, int mLikes) {
        this.mimgeUrl = imgeUrl;
        this.mCreator = mCreator;
        this.mLikes = mLikes;
    }

    public String getMimgeUrl() {
        return mimgeUrl;
    }

    public String getmCreator() {
        return mCreator;
    }

    public int getmLikes() {
        return mLikes;
    }

}
