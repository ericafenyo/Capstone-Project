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

/**
 * Created by eric on 07/02/2018.
 *
 */

public class ArticleDbHelper extends SQLiteOpenHelper {

    /* This is the name of our article database */
    public static final String DATABASE_NAME = "article.db";

    /*
     * If you change the database schema, you must increment the database version or the onUpgrade
     * method will not be called.
     */
    private static final int DATABASE_VERSION = 1;

    public ArticleDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createLatestArticleTable());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QHContracts.ArticleEntry.TABLE_NAME);
        onCreate(db);
    }

    /**
     * @return a simple SQL statement that will create a table
     */
    private String createLatestArticleTable(){

        return "CREATE TABLE " +

                QHContracts.ArticleEntry.TABLE_NAME +

                " (" +

                QHContracts.ArticleEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +

                QHContracts.ArticleEntry.COLUMN_NAME_SOURCE + " TEXT," +

                QHContracts.ArticleEntry.COLUMN_NAME_AUTHOR + " TEXT," +

                QHContracts.ArticleEntry.COLUMN_NAME_TITLE + " TEXT," +

                QHContracts.ArticleEntry.COLUMN_NAME_DESCRIPTION + " TEXT," +

                QHContracts.ArticleEntry.COLUMN_NAME_URL + " TEXT, " +

                QHContracts.ArticleEntry.COLUMN_NAME_URL_TO_IMAGE + " TEXT, " +

                QHContracts.ArticleEntry.COLUMN_NAME_PUBLISHED_DATE + " TEXT " +

                ")";
    }
}
