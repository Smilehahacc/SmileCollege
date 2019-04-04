package com.example.smilecollege.frament;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.smilecollege.R;
import com.example.smilecollege.base.BaseFragment;

public class NotificationsFragment extends BaseFragment {

    static NotificationsFragment instance;
    public static NotificationsFragment getInstance() {
        if (instance == null) {
            instance = new NotificationsFragment();
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
        View view=inflater.inflate(R.layout.fragment_notifications,container,false);
        return view;
    }

}
