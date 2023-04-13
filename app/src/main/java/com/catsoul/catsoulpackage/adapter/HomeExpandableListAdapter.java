package com.catsoul.catsoulpackage.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.catsoul.catsoulpackage.R;
import com.catsoul.catsoulpackage.ui.weather.WeatherActivity;

import java.util.List;

public class HomeExpandableListAdapter extends BaseExpandableListAdapter {
    private List<String> groupData;
    private List<List<String>>  childData;
    private Context context;
    View.OnClickListener monClickListener;

    public HomeExpandableListAdapter(){

    }
    public HomeExpandableListAdapter(List<String> groupData,List<List<String>> childData,Context context){
        this.groupData = groupData;
        this.childData = childData;
        this.context = context;
    }
    public HomeExpandableListAdapter(List<String> groupData,List<List<String>> childData,Context context,View.OnClickListener monClickListener){
        this.groupData = groupData;
        this.childData = childData;
        this.context = context;
        this.monClickListener = monClickListener;
    }




    //组的数量
    @Override
    public int getGroupCount() {
        return groupData.size();
    }

    //某组中子项的数量
    @Override
    public int getChildrenCount(int i) {
        return childData.get(i).size();
    }

    //某组
    @Override
    public Object getGroup(int i) {
        return groupData.get(i);
    }

    //某子项
    @Override
    public Object getChild(int i, int i1) {
        return childData.get(i).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.item_elv_group,null);
        }
        TextView textView = view.findViewById(R.id.elv_group_text);
        textView.setText(groupData.get(i));
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.item_elv_child,viewGroup,false);
        }
        TextView textView = view.findViewById(R.id.elv_child_tv);
        textView.setText(childData.get(i).get(i1));

        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;    //默认返回false,改成true表示组中的子条目可以被点击选中
    }
}
