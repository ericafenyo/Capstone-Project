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

import com.example.eric.quickheadline.data.AppModule;
import com.example.eric.quickheadline.data.net.ApiEndpoint;
import com.example.eric.quickheadline.jobservice.WeatherSyncTask;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by eric on 05/02/2018.
 */

@Component(modules = {AppModule.class,NetworkModule.class})
@Singleton
public interface NetworkComponent {
    void inject(QHViewModel viewModel);
    void inject(WeatherSyncTask weatherSyncTask);

    @Named("article")
    ApiEndpoint getArticleEndpoint();

    @Named("weather")
    ApiEndpoint getWeatherEndpoint();

    @Named("maps")
    ApiEndpoint getMapEndpoint();

}
