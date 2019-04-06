package com.example.smilecollege.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.example.smilecollege.R;
import com.example.smilecollege.base.BaseActivity;
import com.example.smilecollege.frament.DynamicFragment;
import com.example.smilecollege.frament.HomepageFragment;

import com.example.smilecollege.frament.LoginFragment;
import com.example.smilecollege.frament.RegisterFragment;
import com.jaeger.library.StatusBarUtil;

public class LoginActivity extends BaseActivity {

    // 全局变量声明
    private Fragment[] fragments;
    private int lastfragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_layout);
        initFragment();
        setStatusBar();
        addListener();
    }

    protected void addListener() {
        // 进入登录界面
        findViewById(R.id.button_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchFragment(lastfragment,0);
                lastfragment=0;
            }
        });

        // 进入注册界面
        findViewById(R.id.button_register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchFragment(lastfragment,1);
                lastfragment=1;
            }
        });
    }

    protected void setStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // 设置状态栏文字为深色模式
            LoginActivity.this.getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        StatusBarUtil.setTranslucent(LoginActivity.this, 1);
    }

    //    初始化碎片，方便后面调用切换页面
    private void initFragment()
    {
        // 将所有fragment对象封装入一个数组
        fragments = new Fragment[]{
                LoginFragment.getInstance(),
                RegisterFragment.getInstance(),
        };
        lastfragment = 0;
//        // 提交事务，设置起始页碎片布局
//        getSupportFragmentManager().beginTransaction().replace(R.id.login_view,fragments[0]).show(fragments[0]).commit();
    }

    // 切换碎片
    private void switchFragment(int lastfragment,int index)
    {
        FragmentTransaction transaction =getSupportFragmentManager().beginTransaction();
        transaction.hide(fragments[lastfragment]);//隐藏上个Fragment
        if(fragments[index].isAdded()==false)
        {
            transaction.add(R.id.login_view,fragments[index]);
        }
        transaction.show(fragments[index]).commitAllowingStateLoss();

    }


    @Override
    protected int getContentViewId() {
        return 0;
    }

    @Override
    protected int getFragmentContentId() {
        return 0;
    }
}
