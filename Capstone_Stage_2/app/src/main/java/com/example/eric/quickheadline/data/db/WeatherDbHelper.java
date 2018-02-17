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

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by eric on 07/02/2018.
 */

public class WeatherDbHelper extends SQLiteOpenHelper {

    /* This is the name of our weather database */
    public static final String DATABASE_NAME = "weather.db";

    /*
     * If you change the database schema, you must increment the database version or the onUpgrade
     * method will not be called.
     */
    private static final int DATABASE_VERSION = 1;

    public WeatherDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(createCurrentWeatherTable());
        db.execSQL(createDailyWeatherTable());

        Log.e("onCreate", createCurrentWeatherTable());

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QHContracts.CurrentWeatherEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + QHContracts.DailyWeatherEntry.TABLE_NAME);
        onCreate(db);
    }


    /**
     * @return a simple SQL statement that will create a table
     */
    private String createCurrentWeatherTable() {

        return "CREATE TABLE " + QHContracts.CurrentWeatherEntry.TABLE_NAME + " (" +

                QHContracts.CurrentWeatherEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +

                QHContracts.CurrentWeatherEntry.COLUMN_NAME_SUMMARY + " TEXT NOT NULL," +

                QHContracts.CurrentWeatherEntry.COLUMN_NAME_TIMEZONE + " TEXT NOT NULL, " +

                QHContracts.CurrentWeatherEntry.COLUMN_NAME_TEMPERATURE + " REAL NOT NULL, " +

                QHContracts.CurrentWeatherEntry.COLUMN_NAME_APPARENT_TEMPERATURE + " REAL NOT " +
                "NULL, " +

                QHContracts.CurrentWeatherEntry.COLUMN_NAME_HUMIDITY + " REAL NOT " +
                "NULL, " +

                QHContracts.CurrentWeatherEntry.COLUMN_NAME_WIND_SPEED + " REAL NOT " +
                "NULL," +

                QHContracts.CurrentWeatherEntry.COLUMN_NAME_UV_INDEX + " INTEGER NOT " +
                "NULL," +

                QHContracts.CurrentWeatherEntry.COLUMN_NAME_VISIBILITY + " REAL NOT " +
                "NULL," +

                QHContracts.CurrentWeatherEntry.COLUMN_NAME_TIME + " REAL NOT NULL," +

                QHContracts.CurrentWeatherEntry.COLUMN_NAME_ICON + " TEXT NOT NULL," +

                QHContracts.DailyWeatherEntry.COLUMN_NAME_PRESSURE + " REAL NOT NULL" +

                ");";
    }

    /**
     * @returna simple SQL statement that will create a table
     */
    private String createDailyWeatherTable() {

        return "CREATE TABLE " + QHContracts.DailyWeatherEntry.TABLE_NAME + " (" +

                QHContracts.DailyWeatherEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +

                QHContracts.DailyWeatherEntry.COLUMN_NAME_SUMMARY + " TEXT NOT NULL," +

                QHContracts.DailyWeatherEntry.COLUMN_NAME_TEMPERATURE_HIGH + " REAL NOT NULL, " +

                QHContracts.DailyWeatherEntry.COLUMN_NAME_TEMPERATURE_LOW + " REAL NOT NULL, " +

                QHContracts.DailyWeatherEntry.COLUMN_NAME_TIME + " REAL NOT NULL," +

                QHContracts.DailyWeatherEntry.COLUMN_NAME_ICON + " TEXT NOT NULL" +

                ");";
    }


}
