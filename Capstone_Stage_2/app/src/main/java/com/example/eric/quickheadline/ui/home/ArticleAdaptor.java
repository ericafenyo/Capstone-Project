/*
 * Copyright (C) 2018 Eric Afenyo
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package com.example.eric.quickheadline.ui.home;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.eric.quickheadline.GlideApp;
import com.example.eric.quickheadline.R;
import com.example.eric.quickheadline.data.schema.News.Articles;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by eric on 03/02/2018.
 */

public class ArticleAdaptor extends RecyclerView.Adapter<ArticleAdaptor.ArticleViewHolder> {

    private List<Articles> mData;
    private Context mContext;
    private onItemSelected mOnItemSelected;

    //Constructor
    public ArticleAdaptor(Context mContext, onItemSelected mOnItemSelected) {
        this.mContext = mContext;
        this.mOnItemSelected = mOnItemSelected;
    }

    //Setter method
    public void setData(List<Articles> mData) {
        this.mData = mData;
    }

    //Item click listener Interface
    public interface onItemSelected{
        void onClick(int position,List<Articles> articles,ImageView imageView);
    }

    @Override
    public ArticleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.article_list_item, parent,
                false);
        return new ArticleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ArticleViewHolder holder, int position) {
        Articles articles = mData.get(position);
        holder.tvArticleTitle.setText(articles.getTitle());
        holder.tvArticleDescription.setText(articles.getDescription());
        holder.tvArticleSource.setText(articles.getSource().getName());
        holder.tvArticlePublishTime.setText(articles.getPublishedAt());

        //Set shared element transition name on imageView
        ViewCompat.setTransitionName(holder.ivArticleThumbnail,String.valueOf(mData.get(position)));

        GlideApp.with(mContext).load(articles.getUrlToImage())
                .into(holder
                .ivArticleThumbnail);
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public class ArticleViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_article_title)
        TextView tvArticleTitle;
        @BindView(R.id.tv_article_description)
        TextView tvArticleDescription;
        @BindView(R.id.tv_article_source)
        TextView tvArticleSource;
        @BindView(R.id.tv_article_publish_time)
        TextView tvArticlePublishTime;
        @BindView(R.id.iv_article_thumbnail)
        ImageView ivArticleThumbnail;

        public ArticleViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            mOnItemSelected.onClick(getAdapterPosition(),mData,ivArticleThumbnail);
        }
    }
}
