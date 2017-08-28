package com.example.hp.navigationwithtab.modelclass;

import java.io.Serializable;

/**
 * Created by HP on 20/05/2017.
 */

public class NewsDetail implements Serializable{
    int mPosition;
    String mNewstitle;
    String mNewsImage;
    String mNewsPublished;
    String mNewsAuthor;
    String mNewsDescription;

    public NewsDetail(String Newstitle, String NewsImage, String NewsPublished, String NewsAuthor, String NewsUrl,int position) {
        this.mNewstitle = Newstitle;
        this.mNewsImage = NewsImage;
        this.mNewsPublished = NewsPublished;
        this.mNewsAuthor = NewsAuthor;
        this.mNewsDescription = NewsUrl;
        this.mPosition=position;
    }

    public String getmNewstitle() {
        return mNewstitle;
    }

    public void setmNewstitle(String mNewstitle) {
        this.mNewstitle = mNewstitle;
    }

    public String getmNewsImage() {
        return mNewsImage;
    }

    public void setmNewsImage(String mNewsImage) {
        this.mNewsImage = mNewsImage;
    }

    public String getmNewsPublished() {
        return mNewsPublished;
    }

    public void setmNewsPublished(String mNewsPublished) {
        this.mNewsPublished = mNewsPublished;
    }

    public String getmNewsAuthor() {
        return mNewsAuthor;
    }

    public void setmNewsAuthor(String mNewsAuthor) {
        this.mNewsAuthor = mNewsAuthor;
    }

    public String getmNewsDescription() {
        return mNewsDescription;
    }

    public void setmNewsDescription(String mNewsDescription) {
        this.mNewsDescription = mNewsDescription;
    }
}
