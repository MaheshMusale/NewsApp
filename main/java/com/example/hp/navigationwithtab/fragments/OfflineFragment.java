package com.example.hp.navigationwithtab.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.hp.navigationwithtab.R;
import com.example.hp.navigationwithtab.activity.MainActivity;
import com.example.hp.navigationwithtab.activity.NewsDescription;
import com.example.hp.navigationwithtab.adapter.NewsAdapter;
import com.example.hp.navigationwithtab.adapter.OfflineAdapter;
import com.example.hp.navigationwithtab.adapter.OnNewsDetailClickListener;
import com.example.hp.navigationwithtab.database.Database;
import com.example.hp.navigationwithtab.modelclass.News;
import com.example.hp.navigationwithtab.modelclass.NewsCategory;
import com.example.hp.navigationwithtab.modelclass.NewsDetail;

import java.util.ArrayList;

/**
 * Created by Mahesh on 6/1/2017.
 */

public class OfflineFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{

    ArrayList<News> listofNews;
    RecyclerView mRecyclerForNews;
    OfflineAdapter offlineAdapter;
    SwipeRefreshLayout mSwipeLayout;
    NewsDetail mNewsDetail;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        mRecyclerForNews = (RecyclerView) view.findViewById(R.id.NewsRecyclerView);

        listofNews = new ArrayList<>();

        offlineAdapter=new OfflineAdapter(populate(), getActivity());
        mRecyclerForNews.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mRecyclerForNews.setAdapter(offlineAdapter);

        mSwipeLayout= (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
        mSwipeLayout.setOnRefreshListener(this);
        mSwipeLayout.setColorSchemeResources(
                R.color.colorPrimary,
                R.color.colorP,
                R.color.colorA);
        offlineAdapter.setOnNewsDetailClickListner(new MyOnNewsDetailClickListner());


        return view;


    }
    private class MyOnNewsDetailClickListner implements OnNewsDetailClickListener {
        @Override
        public void onNewsDetailClick(View view, int position) {
            Intent intent=new Intent(getContext(),NewsDescription.class);
            mNewsDetail=new NewsDetail(listofNews.get(position).getNewstitle(),listofNews.get(position).getNewsPublished(),listofNews.get(position).getNewsImage(),listofNews.get(position).getNewsAuthor(),listofNews.get(position).getNewsDescription(),position);
            intent.putExtra("NewsDetail",mNewsDetail);
            startActivity(intent);

        }
    }
    public ArrayList<News> populate()
    {
        listofNews.clear();
        Cursor cursor= MainActivity.db.query("news_all",null,null,null,null,null,"News_Id");
        while (cursor.moveToNext())
        {
            News news=new News();
            news.setmNewsId(cursor.getInt(0));
            news.setNewstitle(cursor.getString(1));
            news.setNewsDescription(cursor.getString(2));
            news.setNewsAuthor(cursor.getString(3));
            news.setNewsImage(cursor.getString(4));
            news.setNewsPublished(cursor.getString(5));
            listofNews.add(news);
        }
        return listofNews;
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override public void run() {
                mSwipeLayout.setRefreshing(false);

            }

        }, 5000);

    }
}

