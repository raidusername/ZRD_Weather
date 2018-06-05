package com.weather.zrodo.zrd_weather.db;

import org.litepal.crud.DataSupport;

public class Cities extends DataSupport {
    private int id;
    private String cityName;
    private int citycode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getCitycode() {
        return citycode;
    }

    public void setCitycode(int citycode) {
        this.citycode = citycode;
    }
}
