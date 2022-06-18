package com.example.weatherapp321;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weatherapp321.adapter.FutureWeatherAdapter;
import com.example.weatherapp321.adapter.HoursWeatherAdapter;
import com.example.weatherapp321.bean.BiglunarBean;
import com.example.weatherapp321.bean.DayWeatherBean;
import com.example.weatherapp321.bean.HoursBean;
import com.example.weatherapp321.bean.Lunnarbean;
import com.example.weatherapp321.bean.WeatherBean;
import com.example.weatherapp321.util.LunarUtil;
import com.example.weatherapp321.util.NetUtil;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private AppCompatSpinner mSpinner;
    private ArrayAdapter<String> mSpAdapter;
    private String[] mCities;

    private TextView tvWeather, tvTem, tvTemLowHigh, tvWin, tvAir ,tvLunar;
    private ImageView ivWeather;
    private RecyclerView rlvFutureWeather, rlvhoursWeather;
    private FutureWeatherAdapter mWeatherAdapter;
    private HoursWeatherAdapter mHoursWeatherAdapter;
    private DayWeatherBean todayWeather;

    private Handler mHandler = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                String weather = (String) msg.obj;
                Log.d("fan", "--主线程收到了天气数据-weather---" + weather);
                if (TextUtils.isEmpty(weather)) {
                    Toast.makeText(MainActivity.this, "天气数据为空！", Toast.LENGTH_LONG).show();
                    return;
                }

                Gson gson = new Gson();
                WeatherBean weatherBean = gson.fromJson(weather, WeatherBean.class);
                if (weatherBean != null) {
                    Log.d("fan", "--解析后的数据-weather---" + weatherBean.toString());
                }
                Log.d("cao", "1");
                updateUiOfWeather(weatherBean);

                Toast.makeText(MainActivity.this, "更新天气完成~", Toast.LENGTH_SHORT).show();

            }
            if (msg.what == 2) {
                String Lunar = (String) msg.obj;
                if (TextUtils.isEmpty(Lunar)) {
                    Toast.makeText(MainActivity.this, "农历为空！", Toast.LENGTH_LONG).show();
                    return;
                }
                Log.d("cao", "handleMessage0: "+Lunar);
                Gson gson2 = new Gson();
                BiglunarBean biglunnarbean = gson2.fromJson(Lunar, BiglunarBean.class);
                Log.d("cao", "handleMessage: "+biglunnarbean.toString());
                tvLunar.setText("农历"+biglunnarbean.getDataBean().getLunnarbean().getMonth()+biglunnarbean.getDataBean().getLunnarbean().getDate());
            }

        }
    };

    private void updateUiOfWeather(WeatherBean weatherBean) {
        if (weatherBean == null) {
            return;
        }

        List<DayWeatherBean> dayWeathers = weatherBean.getDayWeathers();

        todayWeather = dayWeathers.get(0);
        List<HoursBean> hoursWeather = todayWeather.getHoursbeans();

        if (todayWeather == null) {
            return;
        }

        tvTem.setText(todayWeather.getTem());
        tvWeather.setText(todayWeather.getWea() + "(" + todayWeather.getDate() + todayWeather.getWeek() + ")");
        tvTemLowHigh.setText(todayWeather.getTem2() + "~" + todayWeather.getTem1());
        tvWin.setText(todayWeather.getWin()[0] + todayWeather.getWinSpeed());
        tvAir.setText("空气:" + todayWeather.getAir() + todayWeather.getAirLevel() + "\n");
        ivWeather.setImageResource(getImgResOfWeather(todayWeather.getWeaImg()));

        dayWeathers.remove(0); // 去掉当天的天气
        hoursWeather.addAll(dayWeathers.get(0).getHoursbeans());

        //rlvhour
        Date date = new Date(); // this object contains the current date value
       // SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        SimpleDateFormat formatter = new SimpleDateFormat("HH");

        String a = formatter.format(date);
        Log.d("cao", "hour="+a);
        while(hoursWeather.get(0).getHours().compareTo(a)<0){
            hoursWeather.remove(0);
        }
        mHoursWeatherAdapter = new HoursWeatherAdapter(this, hoursWeather);
        rlvhoursWeather.setAdapter(mHoursWeatherAdapter);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rlvhoursWeather.setLayoutManager(layoutManager1);


        // 未来天气的展示 rlvfuture
        mWeatherAdapter = new FutureWeatherAdapter(this, dayWeathers);
        rlvFutureWeather.setAdapter(mWeatherAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rlvFutureWeather.setLayoutManager(layoutManager);

    }

    private int getImgResOfWeather(String weaStr) {
        // xue、lei、shachen、wu、bingbao、yun、yu、yin、qing
        int result = 0;
        switch (weaStr) {
            case "qing":
                result = R.drawable.biz_plugin_weather_qing;
                break;
            case "yin":
                result = R.drawable.biz_plugin_weather_yin;
                break;
            case "yu":
                result = R.drawable.biz_plugin_weather_dayu;
                break;
            case "yun":
                result = R.drawable.biz_plugin_weather_duoyun;
                break;
            case "bingbao":
                result = R.drawable.biz_plugin_weather_leizhenyubingbao;
                break;
            case "wu":
                result = R.drawable.biz_plugin_weather_wu;
                break;
            case "shachen":
                result = R.drawable.biz_plugin_weather_shachenbao;
                break;
            case "lei":
                result = R.drawable.biz_plugin_weather_leizhenyu;
                break;
            case "xue":
                result = R.drawable.biz_plugin_weather_daxue;
                break;
            default:
                result = R.drawable.biz_plugin_weather_qing;
                break;
        }

        return result;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initEvent();
    }

    private void initEvent() {

    }

    private void initView() {

        mSpinner = findViewById(R.id.sp_city);
        mCities = getResources().getStringArray(R.array.cities);
        mSpAdapter = new ArrayAdapter<>(this, R.layout.sp_item_layout, mCities);
        mSpinner.setAdapter(mSpAdapter);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedCity = mCities[position];
                LinearLayout layout = (LinearLayout) findViewById(R.id.layout);
                if (selectedCity.equals("南岸"))
                    layout.setBackgroundResource(R.drawable.nanshan);
                else if (selectedCity.equals("嘉定"))
                    layout.setBackgroundResource(R.drawable.jiading);
                else if (selectedCity.equals("滕州"))
                    layout.setBackgroundResource(R.drawable.tengzhou);
                else
                    layout.setBackgroundResource(R.drawable.biz_plugin_weather_shenzhen_bg);
                getWeatherOfCity(selectedCity);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        tvWeather = findViewById(R.id.tv_weather);
        tvAir = findViewById(R.id.tv_air);
        tvTem = findViewById(R.id.tv_tem);
        tvTemLowHigh = findViewById(R.id.tv_tem_low_high);
        tvWin = findViewById(R.id.tv_win);
        tvLunar = findViewById(R.id.lunar);
        ivWeather = findViewById(R.id.iv_weather);
        rlvFutureWeather = findViewById(R.id.rlv_future_weather);
        rlvhoursWeather = findViewById(R.id.rlv_hours_weather);
//        tvAir.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, TipsActivity.class);
//                // 将数据传递给tipsActivity
//                intent.putExtra("tips", todayWeather);
//
//                startActivity(intent);
//
//            }
//        });
        tvWeather.setOnClickListener(this);
        tvAir.setOnClickListener(this);
        tvTem.setOnClickListener(this);
        tvTemLowHigh.setOnClickListener(this);
        tvWin.setOnClickListener(this);
        ivWeather.setOnClickListener(this);
        //  rlvFutureWeather.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.tv_weather || view.getId() == R.id.tv_air || view.getId() == R.id.tv_tem || view.getId() == R.id.tv_tem_low_high || view.getId() == R.id.tv_win || view.getId() == R.id.iv_weather) {
            Intent intent = new Intent(MainActivity.this, TipsActivity.class);
            // 将数据传递给tipsActivity
            intent.putExtra("tips", todayWeather);

            startActivity(intent);

        }
    }

    private void getWeatherOfCity(String selectedCity) {
        // 开启子线程，请求网络
        new Thread(new Runnable() {
            @Override
            public void run() {
                // 请求网络
                String weatherOfCity = NetUtil.getWeatherOfCity(selectedCity);
                // 使用handler将数据传递给主线程
                Message message = Message.obtain();
                message.what = 0;
                message.obj = weatherOfCity;
                mHandler.sendMessage(message);


                String Lunar = LunarUtil.getLunar();
                Log.d("cao", "run: "+ Lunar);
                Message message2 = Message.obtain();
                message2.what = 2;
                message2.obj = Lunar;

                mHandler.sendMessage(message2);
            }
        }).start();

    }
}