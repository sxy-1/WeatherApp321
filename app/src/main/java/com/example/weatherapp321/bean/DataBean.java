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


public class DataBean implements Serializable {
    @SerializedName("lunar")
    private Lunnarbean lunnarbean;

    public Lunnarbean getLunnarbean() {
        return lunnarbean;
    }

    public void setLunnarbean(Lunnarbean lunnarbean) {
        this.lunnarbean = lunnarbean;
    }

    @Override
    public String toString() {
        return "DataBean{" +
                "lunnarbean=" + lunnarbean +
                '}';
    }
}
