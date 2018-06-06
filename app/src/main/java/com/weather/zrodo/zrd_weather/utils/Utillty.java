package com.weather.zrodo.zrd_weather.utils;


import android.text.TextUtils;

import com.weather.zrodo.zrd_weather.db.Cities;
import com.weather.zrodo.zrd_weather.db.County;
import com.weather.zrodo.zrd_weather.db.Provinces;

import org.json.JSONArray;
import org.json.JSONObject;


public class Utillty {
    /**
     * 解析和处理服务器返回的省级接口数据
     */
    public static Boolean jsonprovince(String response) {
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray allProvinces = new JSONArray(response);
                for (int i = 0; i < allProvinces.length(); i++) {
                    JSONObject object = allProvinces.getJSONObject(i);
                    Provinces provinces = new Provinces();
                    provinces.setProvincecode(object.getInt("id"));
                    provinces.setProvinceName(object.getString("name"));
                    provinces.save();

                }
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return false;
    }

    /**
     * 解析和处理服务器返回的市级接口数据
     */
    public static Boolean jsoncity(String response,int provincecode) {
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray allcities = new JSONArray(response);
                for (int i = 0; i < allcities.length(); i++) {
                    JSONObject object = allcities.getJSONObject(i);
                    Cities city = new Cities();
                    city.setCitycode(object.getInt("id"));
                    city.setCityName(object.getString("name"));
                    city.setProvincecode(provincecode);
                    city.save();

                }
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return false;
    }

    /**
     * 解析和处理服务器返回的区级接口数据
     */
    public static Boolean jsoncounty(String response,int citycode) {
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray allcounty = new JSONArray(response);
                for (int i = 0; i < allcounty.length(); i++) {
                    JSONObject object = allcounty.getJSONObject(i);
                    County county = new County();
                    county.setCountycode(object.getInt("id"));
                    county.setCountyName(object.getString("name"));
                    county.setWeatherId(object.getString("weather_id"));
                    county.setCitycode(citycode);
                    county.save();
                }
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
