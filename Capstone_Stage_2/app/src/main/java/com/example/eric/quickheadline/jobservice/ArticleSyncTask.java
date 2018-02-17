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

package com.example.eric.quickheadline.jobservice;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import com.example.eric.quickheadline.MyApp;
import com.example.eric.quickheadline.data.db.QHContracts;
import com.example.eric.quickheadline.data.net.ApiEndpoint;
import com.example.eric.quickheadline.model.Articles;
import com.example.eric.quickheadline.model.News;
import com.example.eric.quickheadline.utils.JsonUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by eric on 14/02/2018.
 */

public class ArticleSyncTask {

    /**
     * @param context The context to use. {@link android.app.Application}
     */
    public static void execute(Context context) {
        ApiEndpoint endpoint = ((MyApp) context).getNetworkComponent().getArticleEndpoint();
        endpoint.getArticle().enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                if (response.body() != null) {
                    //updates our ContentProvider
                    syncArticle(context, response.body().getArticles());
                }
//                Log.e("Current", String.valueOf(response.body().getArticles()));
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {
                Log.e("Current Error", String.valueOf(t));
            }
        });
    }


    /**
     * @param context The context to use. {@link android.app.Application}
     */
    private static void syncArticle(Context context, List<Articles> articles) {

        ContentValues[] articleContentValues = JsonUtils.getArticleContentValues(articles);
        if (articleContentValues != null && articleContentValues.length != 0) {

                    /* Get a handle on the ContentResolver to delete and insert data */
            ContentResolver contentResolver = context.getContentResolver();

            contentResolver.delete(QHContracts.ArticleEntry.CONTENT_URI_LATEST_ARTICLE, null,
                    null);

                    /* Insert our new weather data into Sunshine's ContentProvider */
            contentResolver.bulkInsert(QHContracts.ArticleEntry.CONTENT_URI_LATEST_ARTICLE,
                    articleContentValues);

        }
    }


}
