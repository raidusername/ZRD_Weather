package com.weather.zrodo.zrd_weather.db;

import org.litepal.crud.DataSupport;

public class Provinces extends DataSupport {
    private int id;
    private String provinceName;
    private int provincecode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public int getProvincecode() {
        return provincecode;
    }

    public void setProvincecode(int provincecode) {
        this.provincecode = provincecode;
    }
}
