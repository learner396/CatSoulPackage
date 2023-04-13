package com.catsoul.catsoulpackage.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.catsoul.catsoulpackage.adapter.HomeExpandableListAdapter;
import com.catsoul.catsoulpackage.databinding.FragmentHomeBinding;
import com.catsoul.catsoulpackage.ui.weather.WeatherActivity;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    private List<String> groupData = new ArrayList<String>();
    private List<List<String>> childData = new ArrayList<List<String>>();

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        initData();
        HomeExpandableListAdapter homeExpandableListAdapter = new HomeExpandableListAdapter(groupData,childData,getContext());
        binding.homeElv.setAdapter(homeExpandableListAdapter);

        //在这里点击跳转页面
        binding.homeElv.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                if (childData.get(i).get(i1)=="天气"){
                    startActivity(new Intent(getContext(), WeatherActivity.class));
                }

                return true;
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    //准备数据
    private void initData(){
        groupData.add("常用应用");
        groupData.add("查询应用");
        groupData.add("计算应用");
        groupData.add("图片应用");
        groupData.add("文字应用");
        groupData.add("设备应用");
        groupData.add("文件应用");
        groupData.add("其他应用");

        List<String> childBase1 = new ArrayList<String>();
        List<String> childBase2 = new ArrayList<String>();
        List<String> childBase3 = new ArrayList<String>();
        List<String> childBase4 = new ArrayList<String>();
        List<String> childBase5 = new ArrayList<String>();
        List<String> childBase6 = new ArrayList<String>();
        List<String> childBase7 = new ArrayList<String>();
        List<String> childBase8 = new ArrayList<String>();

        childBase2.add("天气");

        childData.add(childBase1);
        childData.add(childBase2);
        childData.add(childBase3);
        childData.add(childBase4);
        childData.add(childBase5);
        childData.add(childBase6);
        childData.add(childBase7);
        childData.add(childBase8);

    }
}