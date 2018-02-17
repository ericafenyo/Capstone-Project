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

package com.example.eric.quickheadline.utils;

import android.content.ContentValues;

import com.example.eric.quickheadline.model.Articles;
import com.example.eric.quickheadline.model.Currently;
import com.example.eric.quickheadline.model.Daily;
import com.example.eric.quickheadline.model.Data;
import com.example.eric.quickheadline.data.db.QHContracts.DailyWeatherEntry;
import com.example.eric.quickheadline.data.db.QHContracts.CurrentWeatherEntry;
import com.example.eric.quickheadline.data.db.QHContracts.ArticleEntry;

import com.example.eric.quickheadline.model.Weather;

import java.util.List;

/**
 * Created by eric on 08/02/2018.
 *
 */

public class JsonUtils {

    public static ContentValues[] getDailyWeatherContentValues(Weather weather) {

        Daily daily = weather.getDaily();

        List<Data> data = daily.getData();

        ContentValues[] dailyWeatherValues = new ContentValues[data.size()];
        int index = 0;
        for (Data datum : data) {
            String icon = datum.getIcon();
            String summary = datum.getSummary();
            double temperatureHigh = datum.getTemperatureHigh();
            double temperatureLow = datum.getTemperatureLow();
            long time = datum.getTime();

            ContentValues value = new ContentValues();
            value.put(DailyWeatherEntry.COLUMN_NAME_SUMMARY, summary);
            value.put(DailyWeatherEntry.COLUMN_NAME_TEMPERATURE_HIGH, temperatureHigh);
            value.put(DailyWeatherEntry.COLUMN_NAME_TEMPERATURE_LOW, temperatureLow);
            value.put(DailyWeatherEntry.COLUMN_NAME_TIME, time);
            value.put(DailyWeatherEntry.COLUMN_NAME_ICON, icon);

            dailyWeatherValues[index++] = value;

        }
        return dailyWeatherValues;

    }

    public static ContentValues getCurrentWeatherContentValues(Weather weather) {

        String timezone = weather.getTimezone();
        Currently currently = weather.getCurrently();
        double apparentTemperature = currently.getApparentTemperature();
        double humidity = currently.getHumidity();
        String icon = currently.getIcon();
        String summary = currently.getSummary();
        double temperature = currently.getTemperature();
        long time = currently.getTime();
        int uvIndex = currently.getUvIndex();
        double pressure = currently.getPressure();
        double visibility = currently.getVisibility();
        double windSpeed = currently.getWindSpeed();

        ContentValues values = new ContentValues();
        values.put(CurrentWeatherEntry.COLUMN_NAME_TIMEZONE, timezone);
        values.put(CurrentWeatherEntry.COLUMN_NAME_TIME, time);
        values.put(CurrentWeatherEntry.COLUMN_NAME_APPARENT_TEMPERATURE, apparentTemperature);
        values.put(CurrentWeatherEntry.COLUMN_NAME_HUMIDITY, humidity);
        values.put(CurrentWeatherEntry.COLUMN_NAME_ICON, icon);
        values.put(CurrentWeatherEntry.COLUMN_NAME_SUMMARY, summary);
        values.put(CurrentWeatherEntry.COLUMN_NAME_TEMPERATURE, temperature);
        values.put(CurrentWeatherEntry.COLUMN_NAME_UV_INDEX, uvIndex);
        values.put(CurrentWeatherEntry.COLUMN_NAME_VISIBILITY, visibility);
        values.put(CurrentWeatherEntry.COLUMN_NAME_WIND_SPEED, windSpeed);
        values.put(CurrentWeatherEntry.COLUMN_NAME_PRESSURE, pressure);
        return values;
    }


    public static ContentValues[] getArticleContentValues(List<Articles> articles) {

        int index = 0;
        ContentValues[] newsArticleValues = new ContentValues[articles.size()];
        for (Articles article : articles) {
            String author = article.getAuthor();
            String title = article.getTitle();
            String description = article.getDescription();
            String url = article.getUrl();
            String publishedAt = article.getPublishedAt();
            String urlToImage = article.getUrlToImage();
            String sourceName = article.getSource().getName();

            ContentValues values = new ContentValues();
            values.put(ArticleEntry.COLUMN_NAME_URL_TO_IMAGE, urlToImage);
            values.put(ArticleEntry.COLUMN_NAME_URL, url);
            values.put(ArticleEntry.COLUMN_NAME_DESCRIPTION, description);
            values.put(ArticleEntry.COLUMN_NAME_SOURCE, sourceName);
            values.put(ArticleEntry.COLUMN_NAME_PUBLISHED_DATE, publishedAt);
            values.put(ArticleEntry.COLUMN_NAME_TITLE, title);
            values.put(ArticleEntry.COLUMN_NAME_AUTHOR, author);

            newsArticleValues[index++] = values;

        }

        return newsArticleValues;
    }
}
