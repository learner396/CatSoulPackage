package com.catsoul.catsoulpackage.ui.dashboard;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.catsoul.catsoulpackage.databinding.FragmentDashboardBinding;

import java.io.IOException;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;

    private String weatherKey = "462645830b4c09483129e81622fdd030";

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
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            String res="获取纬度："+aMapLocation.getLatitude()+"\n获取经度："+aMapLocation.getLongitude()
                                    +"\n获取定位精度："+aMapLocation.getAccuracy()+"\n获取海拔高度信息："+aMapLocation.getAltitude()
                                    +"\n获取室内定位建筑物Id："+aMapLocation.getBuildingId()+"\n获取室内定位楼层："+aMapLocation.getFloor()
                                    +"\n获取地址描述："+aMapLocation.getAddress()+"\n获取国家名称："+aMapLocation.getCountry()
                                    +"\n获取省名称："+aMapLocation.getProvince()+"\n获取城市名称："+aMapLocation.getCity()
                                    +"\n获取城区名称："+aMapLocation.getDistrict()+"\n获取街道名称："+aMapLocation.getStreet()
                                    +"\n获取街道门牌号信息："+aMapLocation.getStreetNum()+"\n获取城市编码信息："+aMapLocation.getCityCode()
                                    +"\n获取区域编码信息："+aMapLocation.getAdCode()+"\n获取当前位置的POI名称："+aMapLocation.getPoiName()
                                    +"\n获取当前位置所处AOI名称："+aMapLocation.getAoiName()+"\n获取定位结果来源："+aMapLocation.getLocationType()
                                    +"\n定位信息描述："+aMapLocation.getLocationDetail()
                                    ;
                            binding.onlyTv.setText(res);
                        }
                    });

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
            if (ContextCompat.checkSelfPermission(this.getActivity(),permission)!= PackageManager.PERMISSION_GRANTED){
                return true;
            }
        }
        return false;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //android8.0之后需要动态获取定位权限
        if (lackPermission(permissions)){
            ActivityCompat.requestPermissions(this.getActivity(),permissions,OPEN_SET_REQUEST_CODE);
        }
        getLocation();

        return root;
    }

    public void getLocation(){

        //必要的
        AMapLocationClient.updatePrivacyShow(getActivity(),true,true);
        AMapLocationClient.updatePrivacyAgree(getActivity(),true);

        try {
            //初始化定位
            mLocationClient = new AMapLocationClient(getActivity());
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
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        mLocationClient.onDestroy();
    }
}