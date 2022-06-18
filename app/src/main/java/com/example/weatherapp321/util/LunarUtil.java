package com.example.weatherapp321.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class LunarUtil {

    public static final String url = "https://api.xygeng.cn/day";

    public static String doGetlunar(String urlStr) {
        String result = "";
        HttpURLConnection connection = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        // 连接网络
        try {
            URL urL = new URL(urlStr);
            connection = (HttpURLConnection) urL.openConnection();
            connection.setRequestMethod("POST");
            connection.setConnectTimeout(5000);

            // 从连接中读取数据(二进制)
            InputStream inputStream = connection.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream);
            // 二进制流送入缓冲区
            bufferedReader = new BufferedReader(inputStreamReader);

            // 从缓存区中一行行读取字符串
            StringBuilder stringBuilder = new StringBuilder();
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            result = stringBuilder.toString();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }

            if (inputStreamReader != null) {
                try {
                    inputStreamReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

        return result;
    }


    public static String getLunar() {
        String lunarResult = doGetlunar(url);
        return lunarResult;
    }

}
