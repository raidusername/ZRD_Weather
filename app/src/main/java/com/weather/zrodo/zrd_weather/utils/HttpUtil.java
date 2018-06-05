package com.weather.zrodo.zrd_weather.utils;

import android.support.annotation.Nullable;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpUtil {
    public static String sendrequetwithokhttp(String host,@Nullable RequestBody body){
        String response=null;
        OkHttpClient okHttpClient=null;
        Response responses = null;
        Request request=null;
        if (body==null){
            okHttpClient = new OkHttpClient();
            request = new Request.Builder()
                    .url(host)
                    .build();

        }else {
            okHttpClient = new OkHttpClient();
            request = new Request.Builder()
                    .url(host)
                    .post(body)
                    .build();
        }

        try {
            responses = okHttpClient.newCall(request).execute();
            response = responses.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return response;
    }
}
