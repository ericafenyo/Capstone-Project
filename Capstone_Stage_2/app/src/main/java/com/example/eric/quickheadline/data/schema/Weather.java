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

package com.example.eric.quickheadline.data.schema;

import com.google.gson.annotations.SerializedName;

/**
 * Created by eric on 02/02/2018.
 */

public class Weather {

    @SerializedName("latitude")
    private double latitude;
    @SerializedName("longitude")
    private double longitude;
    @SerializedName("timezone")
    private String timezone;
    @SerializedName("currently")
    private Currently currently;


    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getTimezone() {
        return timezone;
    }

    public Currently getCurrently() {
        return currently;
    }


    public static class Currently {
        @SerializedName("time")
        private int time;
        @SerializedName("summary")
        private String summary;
        @SerializedName("icon")
        private String icon;
        @SerializedName("nearestStormDistance")
        private int nearestStormDistance;
        @SerializedName("nearestStormBearing")
        private int nearestStormBearing;
        @SerializedName("precipIntensity")
        private int precipIntensity;
        @SerializedName("precipProbability")
        private int precipProbability;
        @SerializedName("temperature")
        private double temperature;
        @SerializedName("apparentTemperature")
        private double apparentTemperature;
        @SerializedName("dewPoint")
        private double dewPoint;
        @SerializedName("humidity")
        private double humidity;
        @SerializedName("pressure")
        private double pressure;
        @SerializedName("windSpeed")
        private double windSpeed;
        @SerializedName("windGust")
        private double windGust;
        @SerializedName("windBearing")
        private int windBearing;
        @SerializedName("cloudCover")
        private double cloudCover;
        @SerializedName("uvIndex")
        private int uvIndex;
        @SerializedName("visibility")
        private double visibility;
        @SerializedName("ozone")
        private double ozone;

        public int getTime() {
            return time;
        }

        public String getSummary() {
            return summary;
        }

        public String getIcon() {
            return icon;
        }

        public int getNearestStormDistance() {
            return nearestStormDistance;
        }

        public int getNearestStormBearing() {
            return nearestStormBearing;
        }

        public int getPrecipIntensity() {
            return precipIntensity;
        }

        public int getPrecipProbability() {
            return precipProbability;
        }

        public double getTemperature() {
            return temperature;
        }

        public double getApparentTemperature() {
            return apparentTemperature;
        }

        public double getDewPoint() {
            return dewPoint;
        }

        public double getHumidity() {
            return humidity;
        }

        public double getPressure() {
            return pressure;
        }

        public double getWindSpeed() {
            return windSpeed;
        }

        public double getWindGust() {
            return windGust;
        }

        public int getWindBearing() {
            return windBearing;
        }

        public double getCloudCover() {
            return cloudCover;
        }

        public int getUvIndex() {
            return uvIndex;
        }

        public double getVisibility() {
            return visibility;
        }

        public double getOzone() {
            return ozone;
        }
    }
}
