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

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.database.Cursor;
import android.support.annotation.NonNull;

import com.example.eric.quickheadline.data.net.ApiEndpoint;
import com.example.eric.quickheadline.model.Bookmark;
import com.example.eric.quickheadline.model.News;
import com.example.eric.quickheadline.model.ObjectModel;
import com.example.eric.quickheadline.model.Weather;
import com.example.eric.quickheadline.viewmodel.BookmarkViewModelContract;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by eric on 01/02/2018.
 *
 */

public class QHViewModel extends AndroidViewModel{

    private MutableLiveData<Weather> mData;
    private MutableLiveData<News> mNewsData;
    private MutableLiveData<Cursor> mCursor;
    private MutableLiveData<List<Bookmark>> mBookmark;

    @Inject
    @Named("article")
    ApiEndpoint article_endpoint;

    @Inject
    @Named("weather")
    ApiEndpoint weather_endpoint;


    public QHViewModel(@NonNull Application application) {
        super(application);
        ((MyApp) getApplication()).getNetworkComponent().inject(this);
        loadData();
    }


    public LiveData<List<Bookmark>> getBookmark() {
        if (mBookmark == null){
            mBookmark = new MutableLiveData<>();
        }
        return mBookmark;
    }

    public LiveData<Weather> getCurrentWeather() {
        if (mData == null) {
            mData = new MutableLiveData<>();

        }
        return mData;
    }


    public LiveData<News> getNewsData() {
        if (mNewsData == null) {
            mNewsData = new MutableLiveData<>();
           loadData();
        }
        return mNewsData;
    }



    public void loadData(){
        Observable<Weather> weatherObservable = weather_endpoint.getObservableWeather()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observable<News> articlesObservable = article_endpoint.getObservableArticle()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observable<ObjectModel> modelObservable = Observable.zip(weatherObservable,
                articlesObservable, new ObjectFunction());

        modelObservable.subscribe(new Observer<ObjectModel>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ObjectModel objectModel) {
               mNewsData.setValue(objectModel.getNews());
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

    }

}
