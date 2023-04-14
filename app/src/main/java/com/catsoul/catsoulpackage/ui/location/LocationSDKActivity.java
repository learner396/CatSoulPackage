package com.catsoul.catsoulpackage.ui.location;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.catsoul.catsoulpackage.R;
import com.catsoul.catsoulpackage.databinding.ActivityLocationSdkBinding;

public class LocationSDKActivity extends AppCompatActivity {

    private ActivityLocationSdkBinding binding;
    private LocationSDKViewModel locationSDKViewModel;

    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;

    //声明AMapLocationClientOption对象
    public AMapLocationClientOption mLocationOption = null;

    //声明定位回调监听器
    public AMapLocationListener mLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            if (aMapLocation!=null){
                if (aMapLocation.getErrorCode() == 0){
                    //获取定位数据，异步更新数据
                    locationSDKViewModel.jy.postValue(aMapLocation.getLatitude()+","+aMapLocation.getLongitude());
                    locationSDKViewModel.accuracy.postValue(String.valueOf(aMapLocation.getAccuracy()));
                    locationSDKViewModel.altitude.postValue(String.valueOf(aMapLocation.getAltitude()));
                    locationSDKViewModel.addressDes.postValue(aMapLocation.getAddress());
                    locationSDKViewModel.address.postValue(aMapLocation.getCountry()+aMapLocation.getProvince()+aMapLocation.getCity()+aMapLocation.getDistrict()+aMapLocation.getStreet()+aMapLocation.getStreetNum());
                    locationSDKViewModel.cityCode.postValue(aMapLocation.getCityCode());
                    locationSDKViewModel.adCode.postValue(aMapLocation.getAdCode());

                }
            }else {
                Log.e("AmapError","location Error, ErrCode:"
                        + aMapLocation.getErrorCode() + ", errInfo:"
                        + aMapLocation.getErrorInfo());
            }
        }
    };

    //动态获取权限
    private String[] permissions = new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_LOCATION_EXTRA_COMMANDS};
    private static final int OPEN_SET_REQUEST_CODE=100;
    public boolean lackPermission(String[] permissions){
        for (String permission: permissions){
            if (ContextCompat.checkSelfPermission(this.getApplicationContext(),permission)!= PackageManager.PERMISSION_GRANTED){
                return true;
            }
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_sdk);



        binding = DataBindingUtil.setContentView(this,R.layout.activity_location_sdk);
        binding.setLifecycleOwner(this);
        //数据与控件绑定，实时显示数据
        locationSDKViewModel = new ViewModelProvider(this).get(LocationSDKViewModel.class);
        binding.setLocationSdk(locationSDKViewModel);

        setVariable();

    }

    private void setVariable(){
        binding.locationSdkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //android8.0之后需要动态获取定位权限
                if (lackPermission(permissions)){
                    ActivityCompat.requestPermissions(LocationSDKActivity.this,permissions,OPEN_SET_REQUEST_CODE);
                }
                getLocation();
            }
        });


    }

    public void getLocation(){

        //必要的
        AMapLocationClient.updatePrivacyShow(getApplicationContext(),true,true);
        AMapLocationClient.updatePrivacyAgree(getApplicationContext(),true);

        try {
            //初始化定位
            mLocationClient = new AMapLocationClient(getApplicationContext());
        }catch (Exception e){
            throw new RuntimeException(e);
        }

        //初始化AMapLocationClientOption对象
        mLocationOption = new AMapLocationClientOption();
        //高精度定位模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置单次定位
        mLocationOption.setOnceLocation(true);
        //设置是否开启定位缓存机制
        mLocationOption.setLocationCacheEnable(false);

        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener);
        //启动定位
        mLocationClient.startLocation();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
        mLocationClient.onDestroy();
    }
}