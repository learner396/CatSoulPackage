package com.catsoul.catsoulpackage.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    public MutableLiveData<String> mText= new MutableLiveData<>();

    public HomeViewModel() {
    }

    public LiveData<String> getText() {
        return mText;
    }
}