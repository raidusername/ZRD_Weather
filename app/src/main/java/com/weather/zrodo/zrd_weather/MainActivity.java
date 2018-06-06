package com.weather.zrodo.zrd_weather;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
private TextView textcity,textchange;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        textchange.setOnClickListener(this);

    }
    public void init(){
       // 初始化
        textcity=(TextView) findViewById(R.id.text_city);
        textchange=(TextView) findViewById(R.id.citychange);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.citychange:


                break;
                default:
        }

    }
}
