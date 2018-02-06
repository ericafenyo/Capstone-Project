package com.example.eric.quickheadline;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.eric.quickheadline.data.schema.News;
import com.example.eric.quickheadline.ui.home.ArticleAdaptor;
import com.example.eric.quickheadline.utils.TUtils;
import com.example.eric.quickheadline.viewmodel.QHViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by eric on 03/02/2018.
 */

public class HomeFragment extends Fragment implements ArticleAdaptor.onItemSelected{

    private static final String ARTICLE_IMAGE_TRANSITION_NAME = "ARTICLE_IMAGE_TRANSITION_NAME";
    private static final String EXTRA_ARTICLE_ITEM = "EXTRA_ARTICLE_ITEM";
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    private ArticleAdaptor mAdaptor;

    public HomeFragment() {
        //Empty public constructor
    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.v("HomeFragment.class","news.toString()");
        QHViewModel model = ViewModelProviders.of(getActivity()).get(QHViewModel.class);

        model.getNewsData().observe(getActivity(), new Observer<News>() {
            @Override
            public void onChanged(@Nullable News news) {
                progressBar.setVisibility(View.INVISIBLE);
                mAdaptor = new ArticleAdaptor(getActivity(),HomeFragment.this);
                mAdaptor.setData(news.getArticles());
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                recyclerView.setHasFixedSize(true);
                recyclerView.setAdapter(mAdaptor);
                recyclerView.setNestedScrollingEnabled(false);
            }
        });
    }

    @Override
    public void onClick(int position, List<News.Articles> articles, ImageView imageView) {
        Intent intent = new Intent(getActivity(),ArticleDetailActivity.class);
        intent.putExtra(ARTICLE_IMAGE_TRANSITION_NAME,String.valueOf(articles.get(position)));
        intent.putExtra(EXTRA_ARTICLE_ITEM,articles.get(position).getUrlToImage());

        ActivityOptionsCompat optionsCompat = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation
                    (getActivity(),imageView,imageView.getTransitionName());
        }
        startActivity(intent,optionsCompat.toBundle());
    }
}
