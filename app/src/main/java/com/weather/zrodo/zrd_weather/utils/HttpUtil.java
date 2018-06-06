package com.weather.zrodo.zrd_weather.utils;



import android.util.Log;

import java.io.IOException;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import okhttp3.Response;

public class HttpUtil {
    public static void sendrequetwithokhttp(String host, Callback callback){
        OkHttpClient client=new OkHttpClient();
        Request request=new Request.Builder().url(host).build();
//        try {
//            Response response=client.newCall(request).execute();
//            Log.d("qureyFtommService", response.body().toString());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        client.newCall(request).enqueue(callback);
    }
}
