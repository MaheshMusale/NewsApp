package com.example.hp.navigationwithtab.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.hp.navigationwithtab.R;
import com.example.hp.navigationwithtab.modelclass.News;

import java.util.ArrayList;

/**
 * Created by Mahesh on 6/2/2017.
 */

public class OfflineAdapter extends RecyclerView.Adapter<OfflineAdapter.NewsHolder> {
    ArrayList<News> mListNews;
    Context mContext;
    OnNewsDetailClickListener mNewsDetailClickListener;

    public OfflineAdapter(ArrayList<News> ListNews, Context Context) {
        this.mListNews = ListNews;
        this.mContext = Context;
    }

    @Override
    public OfflineAdapter.NewsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_layout,null);
        return new OfflineAdapter.NewsHolder(view);
    }


    public void onBindViewHolder(OfflineAdapter.NewsHolder holder, int position) {
        holder.mNewsTitle.setText(mListNews.get(position).getNewstitle());
        holder.mNewsAuthor.setText(mListNews.get(position).getNewsAuthor());
        holder.mNewsPublished.setText(mListNews.get(position).getNewsImage());

        String imgurl=mListNews.get(position).getNewsPublished();
        Glide.with(mContext).load(imgurl).placeholder(R.drawable.placeholder).thumbnail(0.5f).crossFade().diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.mNewsImageView);



    }

    @Override
    public int getItemCount() {
        return mListNews.size();
    }

    public class NewsHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView mNewsImageView;
        TextView mNewsTitle, mNewsAuthor, mNewsPublished;
        public NewsHolder(View itemview) {
            super(itemview);
            mNewsImageView = (ImageView) itemView.findViewById(R.id.imageView);
            mNewsTitle = (TextView) itemView.findViewById(R.id.textview);
            mNewsAuthor = (TextView) itemView.findViewById(R.id.textview1);
            mNewsPublished = (TextView) itemView.findViewById(R.id.textview2);
            itemview.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mNewsDetailClickListener.onNewsDetailClick(v,getPosition());
        }
    }
    public void setOnNewsDetailClickListner(OnNewsDetailClickListener onNewsDetailClickListner){
        mNewsDetailClickListener=onNewsDetailClickListner;
    }
}
