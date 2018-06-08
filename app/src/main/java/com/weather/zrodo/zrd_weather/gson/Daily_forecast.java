package com.weather.zrodo.zrd_weather.gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Daily_forecast {

    public cond cond;
    public String date;
    public tmp tmp;


    public class cond {
        public String txt_d;
    }

    public class tmp {
        public String min;
        public String max;
    }
}
