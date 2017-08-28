package com.example.hp.navigationwithtab.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
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
import com.example.hp.navigationwithtab.activity.NewsDescription;
import com.example.hp.navigationwithtab.adapter.OnNewsDetailClickListener;
import com.example.hp.navigationwithtab.database.Database;
import com.example.hp.navigationwithtab.modelclass.News;
import com.example.hp.navigationwithtab.adapter.NewsAdapter;
import com.example.hp.navigationwithtab.R;
import com.example.hp.navigationwithtab.modelclass.NewsCategory;
import com.example.hp.navigationwithtab.modelclass.NewsDetail;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by HP on 30/04/2017.
 */

public class HomeFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    ArrayList<News> listofNews;
    ArrayList<NewsCategory>Listofcategories;
    RecyclerView mRecyclerForNews;
    NewsAdapter mNewsAdapter;
    int CategoryId;
    String newsurl,categoryurl;
    SwipeRefreshLayout mSwipeLayout;
    RequestQueue mRequestQueue;
    NewsDetail mNewsDetail;
    static int itemposition;
    private ProgressDialog pDialog;
    Database database;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_home, container, false);
        mRecyclerForNews= (RecyclerView) view.findViewById(R.id.NewsRecyclerView);

        listofNews=new ArrayList<>();
        Listofcategories=new ArrayList<>();

        mNewsAdapter =new NewsAdapter(listofNews,getActivity());
        mRecyclerForNews.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        mRecyclerForNews.setAdapter(mNewsAdapter);

        database=new Database();
        database.createtables();

        pDialog = new ProgressDialog(getContext(),R.style.StyledDialog);
        pDialog.setMessage("Loading News...");
        pDialog.setIcon(R.drawable.animation);
        pDialog.show();


        mNewsAdapter.setOnNewsDetailClickListner(new MyOnNewsDetailClickListner());

        mRequestQueue= Volley.newRequestQueue(getContext());

        Bundle bundle=getArguments();
        CategoryId=bundle.getInt("CategoryId");
         if(CategoryId!=0){
             //getnewscategory();
               getTopNews();
               database.showdata();
         }


        //SwipeRefereshLayput
        mSwipeLayout= (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
        mSwipeLayout.setOnRefreshListener(this);
        mSwipeLayout.setColorSchemeResources(
                R.color.colorPrimary,
                R.color.colorP,
                R.color.colorA);




        return view;


    }

    private void getTopNews() {
        if(CategoryId==1)
        {newsurl="http://bitcodetech.in/Newsdatabase20/getallcategory.php?CategoryId=2";}
        if(CategoryId==2)
        {newsurl="http://bitcodetech.in/Newsdatabase20/getallcategory.php?CategoryId=3";}
        if(CategoryId==3)
        {newsurl="http://bitcodetech.in/Newsdatabase20/getallcategory.php?CategoryId=4";}
        if(CategoryId==4)
        {newsurl="http://bitcodetech.in/Newsdatabase20/getallcategory.php?CategoryId=5";}
        if(CategoryId==5)
        {newsurl="http://bitcodetech.in/Newsdatabase20/getallcategory.php?CategoryId=6";}
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, newsurl, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    boolean status=response.getBoolean("status");
                    JSONArray jNews = response.getJSONArray("News");
                    pDialog.dismiss();
                    for(int i=0; i<jNews.length();i++){
                        JSONObject jsonObject=jNews.getJSONObject(i);
                        News news=new News();
                        news.setmNewsId(jsonObject.getInt("NewsId"));
                        news.setNewstitle(jsonObject.getString("NewsTitle"));
                        news.setNewsImage(jsonObject.getString("NewsImage"));
                        news.setNewsPublished(jsonObject.getString("NewsTime"));
                        news.setNewsAuthor(jsonObject.getString("NewsAuthor"));
                        news.setNewsDescription(jsonObject.getString("NewsDescription"));
                        database.addallnews(news);
                        listofNews.add(news);
                        mNewsAdapter.notifyDataSetChanged();
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

      /*  for(News n:listofNews){
            System.out.println(n.getNewstitle()+""+n.getNewsPublished()+""+n.getNewsImage());
            Log.e("Volley",n.getNewsDescription());
        }*/

    }
    public void getnewscategory()
    {
        categoryurl="http://bitcodetech.in/Newsdatabase20/getallcategoryonly.php";
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, categoryurl, new Response.Listener<JSONObject>() {
        @Override
        public void onResponse(JSONObject response) {
            try {
                JSONArray jCategorynews = response.getJSONArray("News");
                for(int i=0; i<jCategorynews.length();i++){
                    JSONObject jsonObject=jCategorynews.getJSONObject(i);
                    NewsCategory newsCategory=new NewsCategory();
                    newsCategory.setmCategoryId(jsonObject.getInt("CategoryId"));
                    newsCategory.setmCategoryName(jsonObject.getString("CategoryName"));
                    System.out.println(newsCategory.getmCategoryName());
                 //   database.addnewscategory(newsCategory);
                    Listofcategories.add(newsCategory);

                    mNewsAdapter.notifyDataSetChanged();
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

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override public void run() {
                mRequestQueue= Volley.newRequestQueue(getContext());
                mSwipeLayout.setRefreshing(false);

            }

        }, 5000);

    }


    private class MyOnNewsDetailClickListner implements OnNewsDetailClickListener {
        @Override
        public void onNewsDetailClick(View view, int position) {
            itemposition=position;
            Intent intent=new Intent(getContext(),NewsDescription.class);
            mNewsDetail=new NewsDetail(listofNews.get(itemposition).getNewstitle(),listofNews.get(itemposition).getNewsImage(),listofNews.get(itemposition).getNewsPublished(),listofNews.get(itemposition).getNewsAuthor(),listofNews.get(itemposition).getNewsDescription(),itemposition);
            intent.putExtra("NewsDetail",mNewsDetail);
            startActivity(intent);

        }
    }
}
