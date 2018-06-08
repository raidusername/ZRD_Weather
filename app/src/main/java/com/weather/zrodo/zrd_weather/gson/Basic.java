package com.weather.zrodo.zrd_weather.gson;

import com.google.gson.annotations.SerializedName;

public class Basic {
    @SerializedName("city")
    public String cityName;
    @SerializedName("id")
    public String weatherid;
    public update update;

    public class update {
        @SerializedName("loc")
        public String updatetime;
    }
}
