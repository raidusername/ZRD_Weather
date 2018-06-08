package com.weather.zrodo.zrd_weather;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.weather.zrodo.zrd_weather.gson.Daily_forecast;
import com.weather.zrodo.zrd_weather.gson.HeWeather;
import com.weather.zrodo.zrd_weather.utils.HttpUtil;
import com.weather.zrodo.zrd_weather.utils.Utillty;
import com.weather.zrodo.zrd_weather.zrd_Interface.Zrd_Interface;

import java.io.File;
import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class WeatherActivity extends AppCompatActivity {
    private ScrollView sv_weather;
    private TextView tv_title;
    private TextView tv_update_time;
    private TextView tv_temp;
    private TextView tv_weatherinfo;
    private TextView tv_comf;
    private TextView tv_wash;
    private TextView tv_sport;
    private TextView tv_date;
    private TextView tv_info;
    private TextView tv_mininfo;
    private TextView tv_maxinfo;
    private LinearLayout LinerLayout_forecast;
    private TextView tv_aqi;
    private TextView tv_pm25;
    public SwipeRefreshLayout refresh;
    public DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.active_weather);
        init();
        refresh.setColorSchemeResources(R.color.colorPrimary);

        final SharedPreferences pre = PreferenceManager.getDefaultSharedPreferences(this);
        String weatherString = pre.getString("weather", null);
        final String weatherid;

        if (weatherString != null) {
            //有缓存，直接解析
            HeWeather heWeather = Utillty.HandleWeatherResponse(weatherString);
            weatherid=heWeather.listWeather.get(0).basic.weatherid;
            showweatherinfo(heWeather);
        } else {
            //无缓存
            weatherid = getIntent().getStringExtra("weather_id");
            sv_weather.setVisibility(View.INVISIBLE);
            requestweather(weatherid);

        }

        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestweather(weatherid);
            }
        });

        tv_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           drawerLayout.openDrawer(GravityCompat.START);
            }
        });

    }

    public void requestweather(final String weatherid) {
        String url = Zrd_Interface.getWeather(weatherid);
        HttpUtil.sendrequetwithokhttp(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        Toast.makeText(WeatherActivity.this, "获取天气失败", Toast.LENGTH_SHORT).show();
                        refresh.setRefreshing(false);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String resopnse = response.body().string();
                Log.d("response1111", resopnse);
                final HeWeather heWeather = Utillty.HandleWeatherResponse(resopnse);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (heWeather != null && heWeather.listWeather.size() > 0) {
                            SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(WeatherActivity.this
                            ).edit();
                            editor.putString("weather", resopnse);
                            editor.apply();
                            showweatherinfo(heWeather);
                        } else {
                            Toast.makeText(WeatherActivity.this, "获取天气失败", Toast.LENGTH_SHORT).show();
                        }
                        refresh.setRefreshing(false);
                    }
                });

            }
        });

    }

    public void showweatherinfo(HeWeather heweather) {
        HeWeather.Weather weather = heweather.listWeather.get(0);
        String cityName = weather.basic.cityName;
        String updatetime = weather.basic.update.updatetime;
        String temp = weather.now.tmp;
        String weatherinfo = weather.now.cond_txt;
        tv_title.setText(cityName);
        tv_update_time.setText(updatetime);
        tv_temp.setText(temp);
        tv_weatherinfo.setText(weatherinfo);

        LinerLayout_forecast.removeAllViews();
        for (Daily_forecast forcast : weather.daily_forecast) {

            View view = LayoutInflater.from(this).inflate(R.layout.forcastitem, LinerLayout_forecast, false);
            tv_date = (TextView) view.findViewById(R.id.tv_date);
            tv_info = (TextView) view.findViewById(R.id.tv_info);
            tv_maxinfo = (TextView) view.findViewById(R.id.tv_maxinfo);
            tv_mininfo = (TextView) view.findViewById(R.id.tv_mininfo);

            tv_date.setText(forcast.date);
            tv_info.setText(forcast.cond.txt_d);
            tv_mininfo.setText(forcast.tmp.min);
            tv_maxinfo.setText(forcast.tmp.max);
            LinerLayout_forecast.addView(view);

        }
        ;
        if (weather.aqi != null) {

            tv_aqi.setText(weather.aqi.city.aqi);
            tv_pm25.setText(weather.aqi.city.pm25);
        }
        String comf = "舒适度：" + weather.suggest.comfort.info;
        String wash = "洗车指数：" + weather.suggest.carWash.info;
        String sport = "运动检疫：" + weather.suggest.sport.info;
        tv_comf.setText(comf);
        tv_wash.setText(wash);
        tv_sport.setText(sport);
        sv_weather.setVisibility(View.VISIBLE);
        Toast.makeText(WeatherActivity.this, "显示成功", Toast.LENGTH_LONG).show();


    }

    public void init() {
        tv_title = (TextView) findViewById(R.id.tv_title_city);
        tv_update_time = (TextView) findViewById(R.id.tv_update_time);
        sv_weather = (ScrollView) findViewById(R.id.sv_weather);
        tv_temp = (TextView) findViewById(R.id.tv_tempture);
        tv_weatherinfo = (TextView) findViewById(R.id.tv_now_info);

        tv_comf = (TextView) findViewById(R.id.tv_comf);
        tv_wash = (TextView) findViewById(R.id.tv_wash);
        tv_sport = (TextView) findViewById(R.id.tv_sport);
        tv_aqi = (TextView) findViewById(R.id.tv_aqi);
        tv_pm25 = (TextView) findViewById(R.id.tv_pm25);
        refresh = (SwipeRefreshLayout) findViewById(R.id.srefresh);
        drawerLayout=(DrawerLayout) findViewById(R.id.drawer);
        LinerLayout_forecast = (LinearLayout) findViewById(R.id.LinerLayout_forecast);
    }
}
