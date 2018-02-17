package com.example.eric.quickheadline;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.example.eric.quickheadline.model.Articles;
import com.example.eric.quickheadline.data.db.QHContracts.CurrentWeatherEntry;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by eric on 03/02/2018.
 */

public class HomeFragment extends Fragment implements ArticleAdaptor.onItemSelected {


    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.toolbar_main)
    Toolbar toolbar;
    @BindView(R.id.tv_timezone)
    TextView tvTimezone;
    @BindView(R.id.tv_summary)
    TextView tvSummary;
    @BindView(R.id.ib_location)
    ImageButton ibLocation;
    @BindView(R.id.iv_icon)
    ImageView ivIcon;
    @BindView(R.id.tv_temperature)
    TextView tvTemperature;
    @BindView(R.id.tv_apparent_temperature)
    TextView tvRealFeel;


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
        ButterKnife.bind(this, view);
        getActivity().getSupportLoaderManager().initLoader(0, null, syncLoaser);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.nav_title_latest);
        toolbar.setNavigationIcon(R.drawable.ic_menu);

    }

    @Override
    public void onStart() {
        super.onStart();
        QHViewModel model = ViewModelProviders.of(getActivity()).get(QHViewModel.class);
        model.getNewsData().observe(getActivity(), news -> {
            progressBar.setVisibility(View.INVISIBLE);
            mAdaptor = new ArticleAdaptor(getActivity(), HomeFragment.this);
            mAdaptor.setData(news.getArticles());
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.setHasFixedSize(true);
            recyclerView.setAdapter(mAdaptor);
            recyclerView.setNestedScrollingEnabled(false);

        });
    }


    @Override
    public void onClick(int position, List<Articles> articles, ImageView imageView) {
        if (articles != null) {
            Intent intent = new Intent(getActivity(), ArticleDetailActivity.class);
            intent.putExtra(ConstantFields.ARTICLE_IMAGE_TRANSITION_NAME, String.valueOf(articles.get(position)));
            intent.putParcelableArrayListExtra(ConstantFields.EXTRA_ARTICLE_ITEM, (ArrayList<? extends
                    Parcelable>) articles);
            intent.putExtra(ConstantFields.EXTRA_ARTICLE_ITEM_POSITION, position);
            intent.putExtra(ConstantFields.EXTRA_ARTICLE_ITEM_SOURCE, articles.get(position)
                    .getSource().getName());


//            ActivityOptionsCompat optionsCompat = null;
//            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
//                optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation
//                        (getActivity(), imageView, imageView.getTransitionName());
//            }

//            startActivity(intent, optionsCompat.toBundle());
            startActivity(intent);
        }
    }

    private android.support.v4.app.LoaderManager.LoaderCallbacks<Cursor> syncLoaser = new android.support.v4.app.LoaderManager.LoaderCallbacks<Cursor>() {

        @Override
        public android.support.v4.content.Loader<Cursor> onCreateLoader(int id, Bundle args) {
            return new SyncLoader(getActivity());
        }

        @Override
        public void onLoadFinished(android.support.v4.content.Loader<Cursor> loader, Cursor data) {
            if (data != null) {
                while (data.moveToNext()){


//                Log.e("not error loader", String.valueOf(data.getString(data.getColumnIndex(CurrentWeatherEntry.COLUMN_NAME_SUMMARY))));

                tvTimezone.setText(data.getString(data.getColumnIndex(CurrentWeatherEntry.COLUMN_NAME_TIMEZONE)));
                tvRealFeel.setText(String.valueOf(data.getString(data.getColumnIndex(CurrentWeatherEntry
                        .COLUMN_NAME_APPARENT_TEMPERATURE))));
                tvTemperature.setText(String.valueOf(data.getString(data.getColumnIndex(CurrentWeatherEntry
                        .COLUMN_NAME_TEMPERATURE))));
                tvSummary.setText(data.getString(data.getColumnIndex(CurrentWeatherEntry.COLUMN_NAME_SUMMARY)));
                Picasso.with(getActivity()).load(data.getString(data.getColumnIndex
                        (CurrentWeatherEntry.COLUMN_NAME_ICON))).into(ivIcon);
            }
        }
        }

        @Override
        public void onLoaderReset(android.support.v4.content.Loader<Cursor> loader) {

        }
    };
}
