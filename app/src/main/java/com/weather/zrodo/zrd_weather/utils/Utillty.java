package com.weather.zrodo.zrd_weather.utils;



import com.google.gson.Gson;
import com.weather.zrodo.zrd_weather.gson.Zrd_Bean_address;


public class Utillty {
    public static void gsonwithjson(String response) {
        Gson gson = new Gson();
        Zrd_Bean_address address = gson.fromJson(response, Zrd_Bean_address.class);
    }
}
