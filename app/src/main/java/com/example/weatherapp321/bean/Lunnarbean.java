package com.example.weatherapp321.bean;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/*
"lunar": {
               "year": "壬寅",
               "animal": "虎",
               "lunar_month": "甲辰",
               "month": "四月",
               "lunar_day": "甲寅",
               "date": "初一",
               "term": null
          },
 */


public class Lunnarbean implements Serializable {
    @SerializedName("month")
    private String month;

    @SerializedName("date")
    private String date;

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Lunnarbean{" +
                "month='" + month + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
