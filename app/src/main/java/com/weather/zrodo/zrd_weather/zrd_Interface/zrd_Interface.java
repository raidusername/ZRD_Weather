package com.weather.zrodo.zrd_weather.zrd_Interface;

public class zrd_Interface {
    private static final String APPKEY="5096a060f448e382e5625ea1e4aeece6";
    private static  String protocol="http://";
    private static String host ="v.juhe.cn/weather/";
    public static String getCities(){
        return protocol+host+"citys/?key="+APPKEY;
    }
}
