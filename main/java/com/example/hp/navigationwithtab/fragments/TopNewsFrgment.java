package com.example.hp.navigationwithtab.fragments;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.hp.navigationwithtab.R;
import com.example.hp.navigationwithtab.activity.MainActivity;
import com.example.hp.navigationwithtab.activity.NewsDescription;
import com.example.hp.navigationwithtab.activity.NewsDescriptionTopNews;
import com.example.hp.navigationwithtab.adapter.NewsAdapter;
import com.example.hp.navigationwithtab.adapter.OnNewsDetailClickListener;
import com.example.hp.navigationwithtab.database.Database;
import com.example.hp.navigationwithtab.modelclass.News;
import com.example.hp.navigationwithtab.modelclass.NewsCategory;
import com.example.hp.navigationwithtab.modelclass.NewsDetail;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Mahesh on 6/2/2017.
 */

public class TopNewsFrgment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    ArrayList<News> listofNews;
    RecyclerView mRecyclerForNews;
    NewsAdapter mNewsAdapter;
    String url;
    SwipeRefreshLayout mSwipeLayout;
    RequestQueue mRequestQueue;
    NewsDetail mNewsDetail;
    static int itemposition;
    private ProgressDialog pDialog;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_home, container, false);
        mRecyclerForNews= (RecyclerView) view.findViewById(R.id.NewsRecyclerView);

        createtable();
        listofNews=new ArrayList<>();

        mNewsAdapter =new NewsAdapter(populate(),getActivity());
        mRecyclerForNews.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        mRecyclerForNews.setAdapter(mNewsAdapter);


       /* pDialog = new ProgressDialog(getContext(),R.style.StyledDialog);
        pDialog.setMessage("Loading News...");
        pDialog.setIcon(R.drawable.animation);
        pDialog.show();*/


        mNewsAdapter.setOnNewsDetailClickListner(new MyOnNewsDetailClickListner());

        mRequestQueue= Volley.newRequestQueue(getContext());
        getallnews();
        populate();

        //SwipeRefereshLayput
        mSwipeLayout= (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
        mSwipeLayout.setOnRefreshListener(this);
        mSwipeLayout.setColorSchemeResources(
                R.color.colorPrimary,
                R.color.colorP,
                R.color.colorA);




        return view;


    }
    private class MyOnNewsDetailClickListner implements OnNewsDetailClickListener {
        @Override
        public void onNewsDetailClick(View view, int position) {
            News news=listofNews.get(position);
            Intent intent=new Intent(getContext(), NewsDescriptionTopNews.class);
            intent.putExtra("News",news);
            startActivity(intent);

        }
    }

    public void createtable() {
        MainActivity.db.execSQL("create table if not exists top_news(News_Id integer  autoincreament,News_Author text,News_Title text,News_Description text,News_Image text,News_DateTime datetime primary key)");
    }

    @Override
    public void onRefresh() {

    }
    public ArrayList<News> populate()
    {
        MainActivity.db.delete("top_news","News_DateTime=?",new String[]{null});
        Cursor cursor=MainActivity.db.query("top_news",null,null,null,null,null,"News_DateTime desc");
        listofNews.clear();
        while (cursor.moveToNext())
        {
            News news=new News();
            news.setmNewsId(cursor.getInt(0));
            news.setNewsAuthor(cursor.getString(1));
            news.setNewstitle(cursor.getString(2));
            news.setNewsDescription(cursor.getString(3));
            news.setNewsImage(cursor.getString(4));
            news.setNewsPublished(cursor.getString(5));
            listofNews.add(news);

        }
        return listofNews;
    }
    public void getallnews()
    {
        url="https://newsapi.org/v1/articles?source=the-times-of-india&sortBy=top&apiKey=f6b8a10b9ef54bd6b9d6fbb3c713cb14";
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jCategorynews = response.getJSONArray("articles");
                    for(int i=0; i<jCategorynews.length();i++){
                        JSONObject jsonObject=jCategorynews.getJSONObject(i);
                        ContentValues contentValues=new ContentValues();
                        contentValues.put("News_Author",jsonObject.getString("author"));
                        contentValues.put("News_Title",jsonObject.getString("title"));
                        contentValues.put("News_Description",jsonObject.getString("url"));
                        contentValues.put("News_Image",jsonObject.getString("urlToImage"));
                        contentValues.put("News_DateTime",jsonObject.getString("publishedAt"));
                        MainActivity.db.insert("top_news",null,contentValues);
                        //pDialog.dismiss();
                    }



                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.e("Volley","Error");

            }
        });
        mRequestQueue.add(jsonObjectRequest);
    }

}

