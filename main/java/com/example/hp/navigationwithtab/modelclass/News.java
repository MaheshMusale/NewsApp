package com.example.hp.navigationwithtab.modelclass;

import java.io.Serializable;

/**
 * Created by HP on 02/05/2017.
 */

public class News implements Serializable {
    int mNewsId;
    String mNewstitle;
    String mNewsImage;
    String mNewsPublished;
    String mNewsAuthor;
    String mNewsDescription;

    public String getNewsDescription() {
        return mNewsDescription;
    }

    public void setNewsDescription(String mNewsUrl) {
        this.mNewsDescription = mNewsUrl;
    }

    public String getNewsAuthor() {
        return mNewsAuthor;
    }

    public void setNewsAuthor(String mNewsAuthor) {
        this.mNewsAuthor = mNewsAuthor;
    }

    public String getNewstitle() {
        return mNewstitle;
    }

    public void setNewstitle(String mNewstitle) {
        this.mNewstitle = mNewstitle;
    }

    public String getNewsImage() {
        return mNewsImage;
    }

    public void setNewsImage(String mNewsImage) {
        this.mNewsImage = mNewsImage;
    }

    public String getNewsPublished() {
        return mNewsPublished;
    }

    public void setNewsPublished(String mNewsPublished) {
        this.mNewsPublished = mNewsPublished;
    }

    public int getmNewsId() {
        return mNewsId;
    }

    public void setmNewsId(int mNewsId) {
        this.mNewsId = mNewsId;
    }
}

