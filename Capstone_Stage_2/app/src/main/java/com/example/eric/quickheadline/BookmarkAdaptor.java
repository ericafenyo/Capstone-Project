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
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.eric.quickheadline.model.Bookmark;
import com.example.eric.quickheadline.model.BookmarkBuilder;
import com.example.eric.quickheadline.utils.TUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by eric on 16/02/2018.
 */

public class BookmarkAdaptor extends RecyclerView.Adapter<BookmarkAdaptor.BookmarkViewHolder>{

    private List<Bookmark> mData;
    private Context mContext;
    private onItemSelected mOnItemSelected;

    public BookmarkAdaptor(Context mContext, onItemSelected mOnItemSelected) {
        this.mContext = mContext;
        this.mOnItemSelected = mOnItemSelected;
    }

    //Item click listener Interface
    public interface onItemSelected {
        void onClick(int position, String webUrl);
    }

    @Override
    public BookmarkViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BookmarkViewHolder(LayoutInflater.from(mContext).inflate(R.layout
                .list_item_bookmak,parent,false));
    }

    @Override
    public void onBindViewHolder(BookmarkViewHolder holder, int position) {

        Bookmark bookmark = mData.get(position);
        holder.tvBookmarkSource.setText(bookmark.getSource());
        holder.tvBookmarkPublishTime.setText(bookmark.getPublishedAt());
        holder.tvBookmarkTitle.setText(bookmark.getTitle());

        Picasso.with(mContext).load(bookmark.getUrlToImage()).into(holder.ivBookmarkThumbnail);

    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size() ;
    }

    public class BookmarkViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.tv_bookmark_title)
        TextView tvBookmarkTitle;
        @BindView(R.id.tv_bookmark_source)
        TextView tvBookmarkSource;
        @BindView(R.id.tv_bookmark_publish_time)
        TextView tvBookmarkPublishTime;
        @BindView(R.id.iv_bookmark_thumbnail)
        ImageView ivBookmarkThumbnail;
        @BindView(R.id.iv_bookmark_delete_item)
        ImageView buttonDeleteItem;

        public BookmarkViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            itemView.setOnClickListener(this);
            buttonDeleteItem.setOnClickListener(this);
        }

        //TODO:add appropriate String
        @Override
        public void onClick(View v) {
            if (v.getId() == buttonDeleteItem.getId()){

                int numRowsDeleted = BookmarkRepository.performDelete(BookmarkDb.getINSTANCE
                                (mContext),
                        getBookmarkToBeDeleted(getAdapterPosition()));
                TUtils.toast(mContext,numRowsDeleted);
                if (numRowsDeleted > 0){

                }

            }else {
                mOnItemSelected.onClick(getAdapterPosition(),"link");

            }
        }
    }

    public void setData(List<Bookmark> mData) {
        this.mData = mData;
        notifyDataSetChanged();
    }

    private Bookmark getBookmarkToBeDeleted(int position){
        return new BookmarkBuilder()
                .setAuthor(mData.get(position).getAuthor())
                .setDescription(mData.get(position).getDescription())
                .setPublishedAt(mData.get(position).getPublishedAt())
                .setSource(mData.get(position).getSource())
                .setTitle(mData.get(position).getTitle())
                .setUrl(mData.get(position).getUrl())
                .setUrlToImage(mData.get(position).getUrlToImage())
                .build();
    }
}
