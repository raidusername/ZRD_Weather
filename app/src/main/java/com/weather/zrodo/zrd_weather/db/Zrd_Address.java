package com.weather.zrodo.zrd_weather.db;

import org.litepal.crud.DataSupport;

public class Zrd_Address extends DataSupport {
    private int id;
    private String province;   //省
    private String city;  //市
    private String district;   //区


    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
