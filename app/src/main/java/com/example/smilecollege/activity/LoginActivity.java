package com.example.smilecollege.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.smilecollege.R;
import com.example.smilecollege.base.BaseActivity;
import com.example.smilecollege.frament.DynamicFragment;
import com.example.smilecollege.frament.HomepageFragment;

import com.example.smilecollege.frament.LoginFragment;
import com.example.smilecollege.frament.LoginMainFragment;
import com.example.smilecollege.frament.RegisterFragment;
import com.example.smilecollege.utils.KeyboardHelper;
import com.jaeger.library.StatusBarUtil;

import java.util.Timer;
import java.util.TimerTask;

public class LoginActivity extends BaseActivity {

    // 全局变量声明
    private Fragment[] fragments;
    private int last_fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_layout);

        initFragment();
        setStatusBar();
        // 设置添加监听器延时
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                addListener();
            }

        }, 400);

    }

    protected void addListener() {
        // 进入登录界面
        findViewById(R.id.button_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchFragment(last_fragment,1);
                last_fragment=1;
            }
        });

        // 进入注册界面
        findViewById(R.id.button_register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchFragment(last_fragment,2);
                last_fragment=2;
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
                LoginMainFragment.getInstance(),
                LoginFragment.getInstance(),
                RegisterFragment.getInstance()
        };
        last_fragment = 0;
//        // 提交事务，设置起始页碎片布局
        getSupportFragmentManager().beginTransaction().replace(R.id.login_view,fragments[0]).show(fragments[0]).commit();
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
