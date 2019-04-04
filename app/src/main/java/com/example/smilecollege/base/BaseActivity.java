package com.example.smilecollege.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;

import com.example.smilecollege.utils.AppManager;

public abstract class BaseActivity extends AppCompatActivity {

    //布局文件ID
    protected abstract int getContentViewId();    //布局中Fragment的ID
    protected abstract int getFragmentContentId();    //添加fragment

    // 创建活动
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppManager.getInstance().addActivity(this); //添加到栈中

        // 隐藏标题栏
        if (getSupportActionBar() != null)
            getSupportActionBar().hide();

        // 状态栏沉浸效果沉浸效果
        // 透明状态栏
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        // 透明导航栏
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
    }

    // 销毁活动
    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getInstance().finishActivity(this); //从栈中移除
    }

    // 简化活动启动的代码，方便进行数据传递
    public static void actionStart(Context context,String data1,String data2) {}

    public void addFragment(BaseFragment fragment) {
        if (fragment != null) {
        getSupportFragmentManager().beginTransaction()
                .replace(getFragmentContentId(), fragment, fragment.getClass().getSimpleName())
                .addToBackStack(fragment.getClass().getSimpleName())
                .commitAllowingStateLoss();
        }
    }

    //移除fragment
    public void removeFragment() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStack();
        } else {
            finish();
        }
    }

    //返回键返回事件
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (KeyEvent.KEYCODE_BACK == keyCode) {
            if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
                finish();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}