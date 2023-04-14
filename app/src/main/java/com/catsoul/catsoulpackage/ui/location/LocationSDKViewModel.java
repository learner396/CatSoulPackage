package com.catsoul.catsoulpackage.ui.location;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LocationSDKViewModel extends ViewModel {

    public MutableLiveData<String> jy = new MutableLiveData<>();
    public MutableLiveData<String> accuracy = new MutableLiveData<>();
    public MutableLiveData<String> altitude = new MutableLiveData<>();
    public MutableLiveData<String> addressDes = new MutableLiveData<>();
    public MutableLiveData<String> address = new MutableLiveData<>();
    public MutableLiveData<String> cityCode = new MutableLiveData<>();
    public MutableLiveData<String> adCode = new MutableLiveData<>();

    public LocationSDKViewModel(){

    }


}
