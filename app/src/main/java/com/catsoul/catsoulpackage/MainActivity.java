package com.catsoul.catsoulpackage;

import android.os.Bundle;
import android.view.MenuItem;

import com.catsoul.catsoulpackage.ui.dashboard.DashboardFragment;
import com.catsoul.catsoulpackage.ui.home.HomeFragment;
import com.catsoul.catsoulpackage.ui.notifications.NotificationsFragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.catsoul.catsoulpackage.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setVariable();
    }

    private void setVariable(){
        MainViewPagerAdapter mainViewPagerAdapter = new MainViewPagerAdapter(this);
        List<Fragment> fragmentList =new ArrayList<>();
        fragmentList.add(new HomeFragment());
        fragmentList.add(new DashboardFragment());
        fragmentList.add(new NotificationsFragment());
        mainViewPagerAdapter.setFragmentList(fragmentList);
        binding.mainVp.setAdapter(mainViewPagerAdapter);

        binding.mainVp.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position){
                    case 0:
                        binding.navView.setSelectedItemId(R.id.navigation_home);
                        break;
                    case 1:
                        binding.navView.setSelectedItemId(R.id.navigation_dashboard);
                        break;
                    case 2:
                        binding.navView.setSelectedItemId(R.id.navigation_notifications);
                        break;
                }
            }
        });
        binding.navView.setOnItemSelectedListener(onItemSelectedListener);
    }

private final NavigationBarView.OnItemSelectedListener onItemSelectedListener = new NavigationBarView.OnItemSelectedListener() {
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.navigation_home:
                binding.mainVp.setCurrentItem(0);
                break;
            case R.id.navigation_dashboard:
                binding.mainVp.setCurrentItem(1);
                break;
            case R.id.navigation_notifications:
                binding.mainVp.setCurrentItem(2);
                break;
        }
        return true;
    }
};

}