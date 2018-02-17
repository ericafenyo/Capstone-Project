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

package com.example.eric.quickheadline.discover;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.eric.quickheadline.model.Source;

import java.util.List;

/**
 * Created by eric on 17/02/2018.
 */

public class ArticleSourceAdapter extends RecyclerView.Adapter<ArticleSourceAdapter.ArticleSourceViewHolder>{
    private List<Source> mData;
    private Context mContext;
    private onItemSelected mOnItemSelected;

    public ArticleSourceAdapter(Context mContext, onItemSelected mOnItemSelected) {
        this.mContext = mContext;
        this.mOnItemSelected = mOnItemSelected;
    }

    public void setData(List<Source> mData) {
        this.mData = mData;
    }

    //Item click listener Interface
    public interface onItemSelected {
        void onClick(int position, String webUrl);
    }




    @Override
    public ArticleSourceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ArticleSourceViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ArticleSourceViewHolder extends RecyclerView.ViewHolder{
        public ArticleSourceViewHolder(View itemView) {
            super(itemView);
        }
    }
}
