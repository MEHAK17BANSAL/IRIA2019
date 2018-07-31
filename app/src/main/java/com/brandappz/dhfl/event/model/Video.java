package com.brandappz.dhfl.event.model;

/**
 * Created by ralphpina on 4/18/14.
 */
public class Video {

    private String mTitle; //title
    private String mThumbNailUrl; // thumbnail url
    private String text; // url to data
    private String mLikeCount; // video likes
    private int mViewCount; // times watched

   /* public Video() {
        mTitle = title;
        *//*mThumbNailUrl = thumbNailUrl;
        mContentData = contentData;
        mLikeCount = likeCount;
        mViewCount = viewCount;*//*
    }*/

    public String getTitle() {
        return mTitle;
    }
    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }
    public String getThumbNailUrl() {
        return mThumbNailUrl;
    }
    public void setThumbNailUrl(String mThumbNailUrl ){
        this.mThumbNailUrl = mThumbNailUrl;
    }
    public String getContentData( ) {
        return text;
    }
    public void setContentData(String video_text) {
        this.text = video_text;
    }
    public String getLikeCount() {
        return mLikeCount;
    }

    public int getViewCount() {
        return mViewCount;
    }

    @Override
    public String toString() {
        return mTitle;
    }
}
