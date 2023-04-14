package com.catsoul.catsoulpackage.ui.location;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;

import com.catsoul.catsoulpackage.R;

import com.catsoul.catsoulpackage.databinding.ActivityLocationBinding;

public class LocationActivity extends AppCompatActivity {

    private ActivityLocationBinding binding;
    private LocationViewModel locationViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_location);

        binding.setLifecycleOwner(this);
        //数据与控件绑定，实时显示数据
        locationViewModel = new ViewModelProvider(this).get(LocationViewModel.class);
        binding.setLocation(locationViewModel);

        setVariable();

    }

    private void setVariable(){
        binding.locationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.locationIpText.getText().toString().equals("")){
                    locationViewModel.getHttp();
                }else {
                    locationViewModel.getIp(binding.locationIpText.toString());
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}