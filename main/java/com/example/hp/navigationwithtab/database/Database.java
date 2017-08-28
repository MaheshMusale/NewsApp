package com.example.hp.navigationwithtab.database;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.hp.navigationwithtab.activity.MainActivity;
import com.example.hp.navigationwithtab.modelclass.News;
import com.example.hp.navigationwithtab.modelclass.NewsCategory;

/**
 * Created by Mahesh on 5/31/2017.
 */

public class Database extends AppCompatActivity {
    public static final String NEWSCATEGORY = "news_category";
    public static final String CATEGORYID = "Category_Id";
    public static final String CATEGORYNAME = "Category_Name";
    public static final String ALLNEWS = "all_news";
    public static final String NEWSID = "news_id";
    public static final String NEWSTITLE = "news_title";
    public static final String NEWSAUTHOR = "news_author";
    public static final String NEWSDATETIME = "news_datetime";
    public static final String NEWSDESCRIPTION = "news_description";
    public static final String NEWSIMAGE = "news_image";





    public void createtables()
    {
       //db=openOrCreateDatabase("db_news",Activity.MODE_PRIVATE,null);
        MainActivity.db.execSQL("create table if not exists news_category(Category_Id integer primary key,Category_Name text)");
        MainActivity.db.execSQL("create table if not exists news_all(News_Id integer primary key,News_Title text,News_Description text,News_Author text,News_DateTime datetime,News_Image text)");
    }
    public void addnewscategory(NewsCategory newscategory)
    {
        ContentValues contentvalues=new ContentValues();
        contentvalues.put("Category_Id",newscategory.getmCategoryId());
        contentvalues.put("Category_Name",newscategory.getmCategoryName());
        long count=MainActivity.db.insert("news_category",null,contentvalues);
        System.out.println("Data Inserted"+count);
    }
    public void addallnews(News news)
    {
        ContentValues contentValues=new ContentValues();
        contentValues.put("News_Id",news.getmNewsId());
        contentValues.put("News_Title",news.getNewstitle());
        contentValues.put("News_Description",news.getNewsDescription());
        contentValues.put("News_Author",news.getNewsAuthor());
        contentValues.put("News_Image",news.getNewsImage());
        contentValues.put("News_DateTime",news.getNewsPublished());
        long count=MainActivity.db.insert("news_all",null,contentValues);
        System.out.println("Data Inserted"+count);
    }
    public void showdata()
    {
        Cursor cursor=MainActivity.db.query("news_all",null,null,null,null,null,"News_Id");
        while (cursor.moveToNext())
        {System.out.println(cursor.getInt(0));}
    }

}