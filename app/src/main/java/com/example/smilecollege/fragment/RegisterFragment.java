package com.example.smilecollege.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.smilecollege.R;
import com.example.smilecollege.activity.LoginActivity;
import com.example.smilecollege.base.BaseFragment;

public class RegisterFragment extends BaseFragment {

    static RegisterFragment instance;
    public static RegisterFragment getInstance() {
        if (instance == null) {
            instance = new RegisterFragment();
        }
        return instance;
    }

    private View view;
    private LoginActivity loginActivity;
    private Toolbar toolbar;
    private TextView textView;
    private Button back;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_register,container,false);
        toolbar = (Toolbar)view.findViewById(R.id.toolbar_base);
        setHasOptionsMenu(true);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        textView = view.findViewById(R.id.toolbar_base_title);
        textView.setText(R.string.title_register_short);

        // 添加监听器
        addListener();
        return view;
    }

    protected void addListener() {

        // 点击返回键回到登录-注册主界面
        back = (Button)view.findViewById(R.id.button_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginActivity = (LoginActivity) getActivity();
                loginActivity.onKeyDown(KeyEvent.KEYCODE_BACK,null);
            }
        });
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

}
