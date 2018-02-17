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

package com.example.eric.quickheadline.data.db;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by eric on 08/02/2018.
 * Defines table and column names for the respective databases.
 * If you change the database schema, you must increment the database version or the onUpgrade
 * method will not be called.
 * <p>
 * 1.{@link ArticleEntry} defines the table contents for the latest news article
 * 2.{@link CurrentWeatherEntry} defines the table contents for the current weather forecast
 * 3.{@link DailyWeatherEntry} defines the table contents for the daily weather forecast
 */

public class QHContracts {

    /*
     * Content authority serves as a name for the provider (Content Provider)
     * Since it is unique to a specific provider, each provider
     * must have it's own dedicated authority
     */
    public static final String ARTICLE_CONTENT_AUTHORITY = "com.example.eric.quickheadline.data.articleprovider";
    public static final String BOOKMARK_CONTENT_AUTHORITY = "com.example.eric.quickheadline.data.db.bookmarkprovider";
    public static final String CURRENT_WEATHER_CONTENT_AUTHORITY = "com.example.eric.quickheadline.data.db.currentweatherprovider";
    public static final String DAILY_WEATHER_CONTENT_AUTHORITY = "com.example.eric.quickheadline.data.db.dailyweatherprovider";

    /*
     * The CONTENT_AUTHORITY is used to create the base of all URI's which apps will use to contact
     * the content provider
     */
    public static final Uri ARTICLE_BASE_CONTENT_URI = Uri.parse("content://" + ARTICLE_CONTENT_AUTHORITY);
    public static final Uri BOOKMARK_BASE_CONTENT_URI = Uri.parse("content://" +
            BOOKMARK_CONTENT_AUTHORITY);
    public static final Uri CURRENT_WEATHER_BASE_CONTENT_URI = Uri.parse("content://" + CURRENT_WEATHER_CONTENT_AUTHORITY);
    public static final Uri DAILY_WEATHER_BASE_CONTENT_URI = Uri.parse("content://" + DAILY_WEATHER_CONTENT_AUTHORITY);


    /*
     * TODO: Read there all the comments and correct mistakes
     * paths that can be appended to the BASE_CONTENT_URI to form valid URIs
     * For instance,
     *      content://com.example.eric.quickheadline/article/
     *      [             BASE_CONTENT_URI          ][PATH_ARTICLE_HEADLINE ]
     */
    public static final String PATH_ARTICLE = "article";
    public static final String PATH_BOOKMARKS = "bookmarks";
    public static final String PATH_CURRENT_WEATHER = "currently";
    public static final String PATH_DAILY_WEATHER = "daily";





    /*
     ********************************************************************************************
     *                                                                                          *
     *                                LATEST—ARTICLE—ENTRY                                      *
     *                                                                                          *
     ********************************************************************************************
     */

    /* Inner class that defines the table contents */
    public static class ArticleEntry implements BaseColumns {

        /* The CONTENT_URI used to query the latest article table from the Content Provider */
        public static final Uri CONTENT_URI_LATEST_ARTICLE = ARTICLE_BASE_CONTENT_URI
                .buildUpon()
                .appendPath(PATH_ARTICLE)
                .build();

        /* Defines the name of the article table. */
        public static final String TABLE_NAME = "article";

        /* TODO: reformat this according to description comment
        Column names for article headlines. All these details are stored
         * as returned by the API*/

        /* source is stored as a String representing the  source the article came from.*/
        public static final String COLUMN_NAME_SOURCE = "source";

        /* title is stored as a String representing the headline or title of the article.*/
        public static final String COLUMN_NAME_TITLE = "title";

        /* STRING: representing a description or snippet from the
        article.*/
        public static final String COLUMN_NAME_DESCRIPTION = "description";

        /* url is stored as a String representing the direct URL to the article.*/
        public static final String COLUMN_NAME_URL = "url";

        /* url_to_image is stored as a String representing the URL to a relevant image for the
        article.*/
        public static final String COLUMN_NAME_URL_TO_IMAGE = "url_to_image";

        /* published_date is stored as a String representing The date and time that the article was published, in UTC (+000)*/
        public static final String COLUMN_NAME_PUBLISHED_DATE = "published_date";

        public static final String COLUMN_NAME_AUTHOR = "author";
    }


    /*
     ********************************************************************************************
     *                                                                                          *
     *                                CURRENT-WEATHER-ENTRY                                     *
     *                                                                                          *
     ********************************************************************************************
     */

    /* Inner class that defines the table contents */
    public static class CurrentWeatherEntry implements BaseColumns {

        /* The CONTENT_URI used to query the current weather table from the Content
        Provider */
        public static final Uri CONTENT_URI_CURRENT_WEATHER = CURRENT_WEATHER_BASE_CONTENT_URI
                .buildUpon()
                .appendPath(PATH_CURRENT_WEATHER)
                .build();

        /* Defines the name of the table. */
        public static final String TABLE_NAME = "currently";

        /* Column names for the current weather. All these details are stored
        * as returned by the API
        */

        /* TIMEZONE is stored as a String representing the IANA timezone name for the
        requested location.*/
        public static final String COLUMN_NAME_TIMEZONE = "timezone";

        /*SUMMARY is stored as a String representing a human-readable text summary of the weather
         condition*/
        public static final String COLUMN_NAME_SUMMARY = "summary";

        /*TEMPERATURE is stored as a double representing the air temperature in degrees Fahrenheit */
        public static final String COLUMN_NAME_TEMPERATURE = "temperature";

        /* APPARENT_TEMPERATURE is stored as a double representing the apparent (or “feels like”) temperature in degrees Fahrenheit */
        public static final String COLUMN_NAME_APPARENT_TEMPERATURE = "apparent_temperature";

        /* HUMIDITY is stored as a double representing the relative humidity, between 0 and 1*/
        public static final String COLUMN_NAME_HUMIDITY = "humidity";

        /* WIND_SPEED is stored as a double in miles per hour */
        public static final String COLUMN_NAME_WIND_SPEED = "wind_speed";

        // UX_INDEX is stored as an integer*/
        public static final String COLUMN_NAME_UV_INDEX = "uv_index";

        /* VISIBILITY is stored as a float representing the average visibility in miles, capped
        at 10 miles. */
        public static final String COLUMN_NAME_VISIBILITY = "visibility";

        /*TIME is stored as a long representing the UNIX time at which this data point begins. */
        public static final String COLUMN_NAME_TIME = "time";

        /* ICON is stored as a String representing a machine-readable text summary of the
        weather condition, suitable for selecting an icon for display. This property
        will have cloudy, partly-cloudy-day, or partly-cloudy-night.etc */
        public static final String COLUMN_NAME_ICON = "icon";

        public static final String COLUMN_NAME_PRESSURE = "pressure";

    }


    /*
     ********************************************************************************************
     *                                                                                          *
     *                                DAILY-WEATHER-ENTRY                                       *
     *                                                                                          *
     ********************************************************************************************
     */

    /* Inner class that defines the table contents */
    public static class DailyWeatherEntry extends CurrentWeatherEntry implements BaseColumns {

        /* The CONTENT_URI used to query the current weather table from the Content
        Provider */
        public static final Uri CONTENT_URI_DAILY_WEATHER = DAILY_WEATHER_BASE_CONTENT_URI
                .buildUpon()
                .appendPath(PATH_DAILY_WEATHER)
                .build();

        /* Defines the name of the table. */
        public static final String TABLE_NAME = "daily";

        /*TEMPERATURE_HIGH is stored as a double representing the daytime high temperature.*/
        public static final String COLUMN_NAME_TEMPERATURE_HIGH = "temperature_high";
        /*TEMPERATURE_LOW is stored as a double representing the overnight low temperature.*/
        public static final String COLUMN_NAME_TEMPERATURE_LOW = "temperature_low";
    }
}
