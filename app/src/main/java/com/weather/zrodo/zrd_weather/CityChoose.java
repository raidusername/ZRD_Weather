package com.weather.zrodo.zrd_weather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;

public class CityChoose extends AppCompatActivity implements BDLocationListener {
private TextView address;
    public LocationClient mlocationClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_choose);
    }

    @Override
    public void onReceiveLocation(BDLocation bdLocation) {



        StringBuffer cPosition = new StringBuffer();

        cPosition.append(bdLocation.getCity());

        address.setText(cPosition);
    }


}
