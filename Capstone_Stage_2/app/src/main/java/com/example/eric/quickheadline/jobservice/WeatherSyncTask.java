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

import com.example.eric.quickheadline.BuildConfig;
import com.example.eric.quickheadline.MyApp;
import com.example.eric.quickheadline.data.db.QHContracts;
import com.example.eric.quickheadline.data.net.ApiEndpoint;
import com.example.eric.quickheadline.data.db.QHContracts.CurrentWeatherEntry;
import com.example.eric.quickheadline.model.Weather;
import com.example.eric.quickheadline.utils.JsonUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by eric on 08/02/2018.
 */

public class WeatherSyncTask {

    synchronized public static void execute(Context context) {

        ApiEndpoint endpoint = ((MyApp) context).getNetworkComponent().getWeatherEndpoint();
        endpoint.getWeather().enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {

                if (response.body() != null){
                    //updates our ContentProvider
                    syncWeather(context,response.body());
                }


//                Log.e("not error", String.valueOf(response.body().getDaily()));
            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {
                Log.e("WeatherSyncTask Error", t.toString());
            }
        });
    }


    /**
     * * inserts the new daily weather information into our ContentProvider.
     *
     * @param context Used to access utility methods and the ContentResolver
     * @param weather {@link Weather} object
     */
    private static void syncWeather(Context context, Weather weather) {


        ContentValues[] dailyWeatherValues = JsonUtils.getDailyWeatherContentValues(weather);
        ContentValues currentWeatherValues = JsonUtils.getCurrentWeatherContentValues(weather);

        if (currentWeatherValues != null && dailyWeatherValues.length != 0) {

                    /* Get a handle on the ContentResolver to delete and insert data */
            ContentResolver contentResolver = context.getContentResolver();

            contentResolver.delete(QHContracts.DailyWeatherEntry.CONTENT_URI_DAILY_WEATHER, null,
                    null);

                    /* Insert our new weather data into Sunshine's ContentProvider */
            contentResolver.bulkInsert(QHContracts.DailyWeatherEntry.CONTENT_URI_DAILY_WEATHER,
                    dailyWeatherValues);


        } else if (currentWeatherValues != null && currentWeatherValues.size() != 0) {

                    /* Get a handle on the ContentResolver to delete and insert data */
            ContentResolver contentResolver = context.getContentResolver();

            contentResolver.delete(CurrentWeatherEntry.CONTENT_URI_CURRENT_WEATHER, null,
                    null);

                    /* Insert our new weather data into Sunshine's ContentProvider */
            contentResolver.insert(CurrentWeatherEntry.CONTENT_URI_CURRENT_WEATHER,
                    currentWeatherValues);

        }
    }
}

