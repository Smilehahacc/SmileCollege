package com.example.smilecollege.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.smilecollege.R;
import com.example.smilecollege.base.BaseFragment;

public class LoginMainFragment extends BaseFragment {

    static LoginMainFragment instance;
    public static LoginMainFragment getInstance() {
        if (instance == null) {
            instance = new LoginMainFragment();
        }
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_login_main,container,false);
        return view;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

}
