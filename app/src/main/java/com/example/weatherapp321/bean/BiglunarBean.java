package com.example.weatherapp321.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/*
{
     "code": 200,
     "data": {
          "date": "2022-5-1",
          "today": true,
          "leap": false,
          "sign": "金牛座",
          "solar": {
               "year": 2022,
               "month": 5,
               "date": 1,
               "day": "星期日"
          },
          "lunar": {
               "year": "壬寅",
               "animal": "虎",
               "lunar_month": "甲辰",
               "month": "四月",
               "lunar_day": "甲寅",
               "date": "初一",
               "term": null
          },
          "festival": [
               "劳动节"
          ]
     },
     "updateTime": 1651398687972
}
 */


public class BiglunarBean implements Serializable {
    @SerializedName("data")
    private DataBean dataBean;

    public DataBean getDataBean() {
        return dataBean;
    }

    public void setDataBean(DataBean dataBean) {
        this.dataBean = dataBean;
    }

    @Override
    public String toString() {
        return "BiglunarBean{" +
                "dataBean=" + dataBean +
                '}';
    }
}

