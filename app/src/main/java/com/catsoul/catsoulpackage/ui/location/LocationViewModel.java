package com.catsoul.catsoulpackage.ui.location;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.catsoul.catsoulpackage.ui.location.model.LocationBean;
import com.catsoul.catsoulpackage.util.HttpUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.Arrays;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class LocationViewModel extends ViewModel {
    private String Url="https://restapi.amap.com/v3/ip?key=462645830b4c09483129e81622fdd030";

    public MutableLiveData<LocationBean> locationBeanMutableLiveData = new MutableLiveData<>();
    public LocationViewModel(){
    }

    public void getIp(String ip){
        sendRequestWithOkHttpIp(Url+"&ip="+ip);
    }

    public void getHttp(){
        sendRequestWithOkHttp(Url);
    }

    public void sendRequestWithOkHttp(String url){
        HttpUtil.sendOkHttpRequest(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseData = response.body().string();
                Gson gson = new Gson();
                LocationBean locationBean = gson.fromJson(responseData,LocationBean.class);
                locationBeanMutableLiveData.postValue(locationBean);
            }
        });
    }

    public void sendRequestWithOkHttpIp(String url){
        HttpUtil.sendOkHttpRequest(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseData = response.body().string();
                Gson gson = new Gson();
                LocationBean locationBean = gson.fromJson(responseData,LocationBean.class);
                locationBeanMutableLiveData.postValue(locationBean);
            }
        });
    }

}
