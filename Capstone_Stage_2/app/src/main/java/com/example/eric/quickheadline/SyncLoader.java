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

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.example.eric.quickheadline.data.db.QHContracts;

/**
 * Created by eric on 14/02/2018.
 */

public class SyncLoader extends AsyncTaskLoader<Cursor>{

    public SyncLoader(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Nullable
    @Override
    public Cursor loadInBackground() {

        try {
            return getContext().getContentResolver().query(QHContracts.CurrentWeatherEntry
                    .CONTENT_URI_CURRENT_WEATHER,null,null,null,null);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Error querying data: ",e.toString());
            return null;
        }
    }

    @Override
    public void deliverResult(@Nullable Cursor data) {
        super.deliverResult(data);
    }
}
