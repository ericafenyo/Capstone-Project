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
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.example.eric.quickheadline.data.db.QHContracts;
import com.example.eric.quickheadline.jobservice.WeatherJobService;
import com.firebase.jobdispatcher.Constraint;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.Lifetime;
import com.firebase.jobdispatcher.Trigger;

import java.util.concurrent.TimeUnit;

/**
 * Created by eric on 09/02/2018.
 */

public class WeatherSyncUtils {


    private static final int SYNC_INTERVAL_HOURS = 2;
    private static final int SYNC_INTERVAL_SECONDS = (int) TimeUnit.HOURS.toSeconds(SYNC_INTERVAL_HOURS);
    private static final int SYNC_FLEXTIME_SECONDS = SYNC_INTERVAL_SECONDS / 3;
    private static final String WEATHER_SYNC_TAG = "weather-sync";

    private static boolean sInitialized;

    public static void scheduleWeatherJobDispatcherSync(@NonNull final Context context) {
        FirebaseJobDispatcher jobDispatcher = new FirebaseJobDispatcher(new GooglePlayDriver(context));

        Job weatherJob = jobDispatcher.newJobBuilder()
                // the JobService that will be called
                .setService(WeatherJobService.class)
                // tag that uniquely identifies the job
                .setTag(WEATHER_SYNC_TAG)
                //this Job should run on any network.
                .setConstraints(Constraint.ON_ANY_NETWORK)
                //persist past a device reboot
                .setLifetime(Lifetime.FOREVER)
                .setRecurring(true)
                .setTrigger(Trigger.executionWindow(
                        SYNC_INTERVAL_SECONDS,
                        SYNC_INTERVAL_SECONDS + SYNC_FLEXTIME_SECONDS))
                .setReplaceCurrent(true)
                /* call the builder's build method to return the Job  once it is ready*/
                .build();

        /* Schedule the Job with the dispatcher */
        jobDispatcher.schedule(weatherJob);

    }

    /**
     * Creates periodic sync tasks and checks to see if an immediate sync is required. If an
     * immediate sync is required, this method will take care of making sure that sync occurs.
     *
     * @param context Context that will be passed to other methods and used to access the
     *                ContentResolver
     */
    synchronized public static void initialize(@NonNull final Context context) {

        /*
         * Only perform initialization once per app lifetime. If initialization has already been
         * performed, we have nothing to do in this method.
         */
        if (sInitialized) return;

        sInitialized = true;

//      COMPLETED (13) Call the method you created to schedule a periodic weather sync
        /*
         * This method call triggers Sunshine to create its task to synchronize weather data
         * periodically.
         */
        scheduleWeatherJobDispatcherSync(context);

        /*
         * We need to check to see if our ContentProvider has data to display in our forecast
         * list. However, performing a query on the main thread is a bad idea as this may
         * cause our UI to lag. Therefore, we create a thread in which we will run the query
         * to check the contents of our ContentProvider.
         */
        Thread checkForEmpty = new Thread(() -> {

            /* URI for every row of weather data in our weather table*/
            Uri contentUriCurrentWeather = QHContracts.CurrentWeatherEntry.CONTENT_URI_CURRENT_WEATHER;


            /* Here, we perform the query to check to see if we have any weather data */
            Cursor cursor = context.getContentResolver().query(
                    contentUriCurrentWeather,
                    null,
                    null,
                    null,
                    null);
            /*
             * A Cursor object can be null for various different reasons. A few are
             * listed below.
             *
             *   1) Invalid URI
             *   2) A certain ContentProvider's query method returns null
             *   3) A RemoteException was thrown.
             *
             * Bottom line, it is generally a good idea to check if a Cursor returned
             * from a ContentResolver is null.
             *
             * If the Cursor was null OR if it was empty, we need to sync immediately to
             * be able to display data to the user.
             */
            if (null == cursor || cursor.getCount() == 0) {
                startImmediateSync(context);
            }

            /* Make sure to close the Cursor to avoid memory leaks! */
            cursor.close();
        });

        /* Finally, once the thread is prepared, fire it off to perform our checks. */
        checkForEmpty.start();
    }

    /**
     * Helper method to perform a sync immediately using an IntentService for asynchronous
     * execution.
     *
     * @param context The Context used to start the IntentService for the sync.
     */
    public static void startImmediateSync(@NonNull final Context context) {
        Intent intentSyncWeatherImmediately = new Intent(context, WeatherSyncIntentService.class);
        context.startService(intentSyncWeatherImmediately);
    }
}
