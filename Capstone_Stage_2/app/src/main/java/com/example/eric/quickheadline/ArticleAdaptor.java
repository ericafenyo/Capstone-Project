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

package com.example.eric.quickheadline;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.eric.quickheadline.model.Articles;

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


    public ArticleAdaptor(Context mContext, onItemSelected mOnItemSelected) {
        this.mContext = mContext;
        this.mOnItemSelected = mOnItemSelected;
    }



    //Item click listener Interface
    public interface onItemSelected {
        void onClick(int position, List<Articles> articles, ImageView imageView);
    }




    public void bindArticle(ArticleViewHolder holder,int position){

        Articles articles = mData.get(position);
        holder.tvArticleTitle.setText(articles.getTitle());
        holder.tvArticleDescription.setText(articles.getDescription());
        holder.tvArticleSource.setText(articles.getSource().getName());
        holder.tvArticlePublishTime.setText(articles.getPublishedAt());


        //Set shared element transition name on imageView
        ViewCompat.setTransitionName(holder.ivArticleThumbnail, String.valueOf(mData.get(position)));
    }



    @Override
    public ArticleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ArticleViewHolder(LayoutInflater.from(mContext).inflate(R.layout
                .list_item_article,parent,false));
    }

    @Override
    public void onBindViewHolder(ArticleViewHolder holder, int position) {
        bindArticle(holder,position);
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }





    public class ArticleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
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
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mOnItemSelected.onClick(getAdapterPosition(), mData, ivArticleThumbnail);
        }
    }

    public void setData(List<Articles> mData) {
        this.mData = mData;
    }
}
