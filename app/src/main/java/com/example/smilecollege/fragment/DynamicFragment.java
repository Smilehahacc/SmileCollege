package com.example.smilecollege.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.smilecollege.R;
import com.example.smilecollege.base.BaseFragment;

public class DynamicFragment extends BaseFragment {

    static DynamicFragment instance;
    public static DynamicFragment getInstance() {
        if (instance == null) {
            instance = new DynamicFragment();
        }
        return instance;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_dynamic,container,false);
        setHasOptionsMenu(true);
        return view;
    }

}
