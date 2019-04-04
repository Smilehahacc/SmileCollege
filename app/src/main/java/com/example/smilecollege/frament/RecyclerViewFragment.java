package com.example.smilecollege.frament;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.smilecollege.R;
import com.example.smilecollege.adapter.RecyclerViewPagerAdapter;
import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewFragment extends Fragment {
    public static Fragment newInstance(){return  new RecyclerViewFragment();}
    final List<Object> items = new ArrayList<>();
    static final int ITEMS = 5;

    RecyclerView mRecyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recyclerview, container, false);
    }
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // 像每个卡片中添加控件
        mRecyclerView=view.findViewById(R.id.recyclerView);
        for (int i=0;i<ITEMS;i++){
            items.add(new Object());

        }

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addItemDecoration(new MaterialViewPagerHeaderDecorator());
        mRecyclerView.setAdapter(new RecyclerViewPagerAdapter(items));
    }
}
