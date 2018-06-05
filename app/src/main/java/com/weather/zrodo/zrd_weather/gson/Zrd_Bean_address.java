package com.weather.zrodo.zrd_weather.gson;

import java.util.List;

public class Zrd_Bean_address {
    private String error_code;

    private String reason;
    private List<zrd_address> addresslist;
    private String resultcode;

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public List<zrd_address> getAddresslist() {
        return addresslist;
    }

    public void setAddresslist(List<zrd_address> addresslist) {
        this.addresslist = addresslist;
    }

    public String getResultcode() {
        return resultcode;
    }

    public void setResultcode(String resultcode) {
        this.resultcode = resultcode;
    }

    public class zrd_address {
        private String id;
        private String province;   //省
        private String city;  //市
        private String district;   //区

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

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
    }
}
