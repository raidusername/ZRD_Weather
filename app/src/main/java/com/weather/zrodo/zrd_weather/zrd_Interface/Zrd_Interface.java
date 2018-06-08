package com.weather.zrodo.zrd_weather.zrd_Interface;

public  class Zrd_Interface {
    private static final String APPKEY="0a0ee2af0a974d0ea509338959ea30a5";
    private static String host ="http://guolin.tech/api";
    public static String getProvincecodes(){
        return host+"/china/";
    }
    public static String getCities(int provincecode){
        return host+"/china/"+provincecode;
    }
    public static String getCounty(int provincecode,int citycode){
        return host+"/china/"+provincecode+"/"+citycode;
    }
    public static String getWeather(String weatherID){
        return host+"/weather?cityid="+weatherID+"&key="+APPKEY;
    }
}
