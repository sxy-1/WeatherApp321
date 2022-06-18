package com.example.weatherapp321.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class HoursBean implements Serializable{
    @SerializedName("hours")
    private String hours;

    @SerializedName("wea")
    private String wea;

    @SerializedName("wea_img")
    private String wea_img;

    @SerializedName("tem")
    private String tem;

    @SerializedName("win")
    private String win;

    @SerializedName("win_speed")
    private String win_speed;

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public String getWea() {
        return wea;
    }

    public void setWea(String wea) {
        this.wea = wea;
    }

    public String getWea_img() {
        return wea_img;
    }

    public void setWea_img(String wea_img) {
        this.wea_img = wea_img;
    }

    public String getTem() {
        return tem;
    }

    public void setTem(String tem) {
        this.tem = tem;
    }

    public String getWin() {
        return win;
    }

    public void setWin(String win) {
        this.win = win;
    }

    public String getWin_speed() {
        return win_speed;
    }

    public void setWin_speed(String win_speed) {
        this.win_speed = win_speed;
    }

    @Override
    public String toString() {
        return "HoursBean{" +
                "hours='" + hours + '\'' +
                ", wea='" + wea + '\'' +
                ", wea_img='" + wea_img + '\'' +
                ", tem='" + tem + '\'' +
                ", win='" + win + '\'' +
                ", win_speed='" + win_speed + '\'' +
                '}';
    }
}
