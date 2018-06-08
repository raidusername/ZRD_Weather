package com.weather.zrodo.zrd_weather.gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HeWeather {
    @SerializedName("HeWeather")
    public List<Weather> listWeather;
    public class Weather{
        public AQI aqi;
        public Basic basic;
        public List<Daily_forecast> daily_forecast;
        public  Now now;
        @SerializedName("suggestion")
        public Suggest suggest;

    }
}
