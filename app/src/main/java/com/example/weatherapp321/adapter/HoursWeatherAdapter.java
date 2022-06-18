package com.example.weatherapp321.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherapp321.R;
import com.example.weatherapp321.bean.HoursBean;

import java.util.List;

public class HoursWeatherAdapter extends RecyclerView.Adapter<HoursWeatherAdapter.HoursViewHolder> {

    private Context mContext;


    private List<HoursBean>  mHoursBean;
    public HoursWeatherAdapter(Context mContext,List<HoursBean>  wHoursBean) {
        this.mContext = mContext;
        this.mHoursBean = wHoursBean;
    }



    @NonNull
    @Override
    public HoursViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.hours_item_layout,parent,false);
        HoursViewHolder hoursViewHolder = new HoursViewHolder(view);
        return hoursViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HoursViewHolder holder, int position) {
/*
  DayWeatherBean weatherBean = mWeatherBeans.get(position);

        holder.tvWeather.setText(weatherBean.getWea());
        holder.tvTem.setText(weatherBean.getTem());
        holder.tvDate.setText("(" + weatherBean.getDate() + weatherBean.getWeek() + ")");
        holder.tvTemLowHigh.setText(weatherBean.getTem2() + "~" + weatherBean.getTem1());
        holder.tvWin.setText(weatherBean.getWin()[0] + weatherBean.getWinSpeed());
        holder.tvAir.setText("空气:" + weatherBean.getAir() + weatherBean.getAirLevel());
        holder.ivWeather.setImageResource(getImgResOfWeather(weatherBean.getWeaImg()));
 */
        HoursBean hoursBean = mHoursBean.get(position);
        holder.tvWeather.setText(hoursBean.getWea());
        holder.tvTem.setText(hoursBean.getTem()+"℃");
        holder.tvDate.setText("(" + hoursBean.getHours()+")" );
        holder.ivWeather.setImageResource(getImgResOfWeather(hoursBean.getWea_img()));
    }

    @Override
    public int getItemCount() {
        return (mHoursBean==null )? 0 :mHoursBean.size();
    }

    class HoursViewHolder extends RecyclerView.ViewHolder{


        TextView tvWeather, tvTem,  tvDate;
        ImageView ivWeather;

        public HoursViewHolder(@NonNull View itemView) {
            super(itemView);

            tvWeather = itemView.findViewById(R.id.tv_weather);
            tvTem = itemView.findViewById(R.id.tv_tem);
            tvDate = itemView.findViewById(R.id.tv_date);
            ivWeather = itemView.findViewById(R.id.iv_weather);

        }
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
}
