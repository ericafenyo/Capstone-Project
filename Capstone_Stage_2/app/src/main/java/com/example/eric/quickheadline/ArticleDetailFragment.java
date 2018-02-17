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
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.eric.quickheadline.model.Articles;
import com.example.eric.quickheadline.model.Bookmark;
import com.example.eric.quickheadline.model.BookmarkBuilder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class ArticleDetailFragment extends Fragment implements View.OnClickListener {

    @BindView(R.id.detail_toolbar)
    Toolbar detailToolbar;
    @BindView(R.id.tv_detail_article_title)
    TextView tvArticleTitle;
    @BindView(R.id.tv_detail_article_author)
    TextView tvDetailArticleAuthor;
    @BindView(R.id.tv_detail_article_description)
    TextView tvDetailArticleDescription;
    @BindView(R.id.tv_detail_article_source)
    TextView tvDetailArticleSource;
    @BindView(R.id.tv_detail_article_published_time)
    TextView tvDetailArticlePublishedTime;
    @BindView(R.id.iv_detail_article_thumbnail)
    ImageView ivDetailArticleThumbnail;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    private String source;
    private String title;
    private String description;
    private String publishedAt;
    private String urlToImage;
    private String url;
    private String author;

    public ArticleDetailFragment() {
        // Required empty public constructor
    }

    public static ArticleDetailFragment newInstance() {
        return new ArticleDetailFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
            ((AppCompatActivity)getActivity()).setSupportActionBar(detailToolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_detail, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                getActivity().onBackPressed();
                break;
        }
        return true;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_article_detail, container, false);
        ButterKnife.bind(this, view);


        Bundle bundle = getActivity().getIntent().getExtras();
        if (bundle != null) {
            List<Articles> data = bundle.getParcelableArrayList(ConstantFields.EXTRA_ARTICLE_ITEM);
            String imageTransitionName = bundle.getString(ConstantFields.ARTICLE_IMAGE_TRANSITION_NAME);
//            Toast.makeText(getApplicationContext(),imageTransitionName,Toast.LENGTH_SHORT).show();

             source = bundle.getString(ConstantFields.EXTRA_ARTICLE_ITEM_SOURCE);

            Articles articles = data.get(bundle.getInt(ConstantFields.EXTRA_ARTICLE_ITEM_POSITION));
             title = articles.getTitle();
             description = articles.getDescription();
             publishedAt = articles.getPublishedAt();
             urlToImage = articles.getUrlToImage();
             url = articles.getUrl();
             author = articles.getAuthor();


            //TODO: comment on this
            tvArticleTitle.setText(title);
            tvDetailArticleDescription.setText(description);
            tvDetailArticlePublishedTime.setText(publishedAt);
            tvDetailArticleSource.setText(source);
            Log.v("what again",String.valueOf(urlToImage));

            GlideApp.with(getActivity()).load(urlToImage).into(ivDetailArticleThumbnail);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                ivDetailArticleThumbnail.setTransitionName(imageTransitionName);
            }
        }

        fab.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        addToBookmark();
    }

    private void addToBookmark() {
        Bookmark bookmark = new BookmarkBuilder()
                .setAuthor(author)
                .setTitle(title)
                .setDescription(description)
                .setPublishedAt(publishedAt)
                .setSource(source)
                .setUrl(url)
                .setUrlToImage(urlToImage)
                .build();

       long[] inserts = BookmarkRepository.performInsert(BookmarkDb.getINSTANCE(getActivity()),
                bookmark);

       if (inserts.length > 0){
           Log.e("TAG",String.valueOf(inserts.length));
//           fab.setBackgroundDrawable(ContextCompat.getDrawable(getActivity(),R.drawable.circle));
           fab.setImageResource(R.drawable.ic_menu);
           fab.setBackgroundTintList(ContextCompat.getColorStateList(getActivity(),R.color.colorPrimary));
       }

    }
}
