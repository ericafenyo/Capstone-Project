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

package com.example.eric.quickheadline.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.eric.quickheadline.MyApp;
import com.example.eric.quickheadline.data.net.ApiEndpoint;
import com.example.eric.quickheadline.data.schema.News;
import com.example.eric.quickheadline.data.schema.Weather;

import javax.inject.Inject;
import javax.inject.Named;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by eric on 01/02/2018.
 *
 */

public class QHViewModel extends AndroidViewModel implements IViewModel {

    private MutableLiveData<Weather> mData;
    private MutableLiveData<News> mNewsData;

    @Inject
    @Named("article")
    ApiEndpoint endpoint;

    public QHViewModel(@NonNull Application application) {
        super(application);
        ((MyApp) getApplication()).getNetworkComponent().inject(this);
    }


    public LiveData<Weather> getCurrentWeather() {
        if (mData == null) {
            mData = new MutableLiveData<>();
            loadCurrentWeather();
        }
        return mData;
    }


    public LiveData<News> getNewsData() {
        if (mNewsData == null) {
            mNewsData = new MutableLiveData<>();
            loadArticle();
        }
        return mNewsData;
    }


    @Override
    public void loadCurrentWeather() {
    }

    @Override
    public void loadArticle() {
        endpoint.getArticle().enqueue(new Callback<News>() {
            @Override
            public void onResponse(@NonNull Call<News> call, @NonNull Response<News> response) {
                mNewsData.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<News> call, @NonNull Throwable t) {
                Log.e("Error",t.toString());
            }
        });
    }
}
