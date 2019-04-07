package com.example.smilecollege.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.smilecollege.R;
import com.example.smilecollege.base.BaseFragment;

public class HomepageFragment extends BaseFragment {

    static HomepageFragment instance;
    public static HomepageFragment getInstance() {
        if (instance == null) {
            instance = new HomepageFragment();
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

    //    private TextView textView;
//    private Button button;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_homepage,container,false);
        setHasOptionsMenu(true);
        return view;
    }

//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        inflater.inflate(R.menu.navigation, menu);
//        super.onCreateOptionsMenu(menu,inflater);
//    }
}
