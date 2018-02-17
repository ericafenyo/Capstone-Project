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

package com.example.eric.quickheadline.data.net;

import com.example.eric.quickheadline.model.News;
import com.example.eric.quickheadline.model.Weather;


import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by eric on 03/02/2018.
 */

public interface ApiEndpoint {
    @GET("/forecast/25c175c9ab9d3ee7c3df79e14708ab77/37.8267,-122.4233?exclude=[minutely,hourly,flags]&lang=en")
    Call<Weather> getWeather();

    @GET("top-headlines?country=us&apiKey=2db932d68b6d49428a3d5f5ab16c2902")
    Call<News> getArticle();

    @GET("/forecast/25c175c9ab9d3ee7c3df79e14708ab77/37.8267,-122.4233?exclude=[minutely,hourly,flags]&lang=en")
    Observable<Weather> getObservableWeather();

    @GET("top-headlines?country=us&apiKey=2db932d68b6d49428a3d5f5ab16c2902")
    Observable<News> getObservableArticle();

}
