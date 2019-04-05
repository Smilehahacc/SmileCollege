package com.example.smilecollege.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.example.smilecollege.R;
import com.example.smilecollege.base.BaseActivity;
import com.example.smilecollege.utils.KeyboardHelper;
import com.jaeger.library.StatusBarUtil;

import java.util.Timer;
import java.util.TimerTask;

public class SearchActivity extends BaseActivity {

    private static boolean Boolean_showInputMethod;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_layout);
        Boolean_showInputMethod = true;

        // 手动设置状态栏颜色
        StatusBarUtil.setColorNoTranslucent(SearchActivity.this,getResources().getColor(R.color.colorPrimaryDark));
        SearchActivity.this.getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        // 按取消按钮之后结束当前活动，即返回上一级
        findViewById(R.id.search_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KeyboardHelper.HideKeyboard(v);
                SearchActivity.this.finish();
            }
        });

        editText = findViewById(R.id.search_edit);
        // 点击搜索栏自动呼出输入法
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = SearchActivity.this;
                KeyboardHelper.showInputMethod(context,v);
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        // 自动弹出键盘,获取焦点
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                Context context = SearchActivity.this;
                View v = getWindow().getDecorView();
                KeyboardHelper.showInputMethod(context,v);
                editText.setFocusable(true);
                editText.setFocusableInTouchMode(true);
            }

        }, 400);
        editText.requestFocus();
    }

    @Override
    protected int getContentViewId() {
        return 0;
    }

    @Override
    protected int getFragmentContentId() {
        return 0;
    }

    // 重写返回的方法，若键盘显示则关闭键盘，若无则返回上级
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if(Boolean_showInputMethod) {
                View v = getWindow().getDecorView();
                KeyboardHelper.HideKeyboard(v);
                editText.clearFocus();
            }else {
                SearchActivity.this.finish();
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    // 使editText点击外部时候失去焦点
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideInput(v, ev)) {//点击editText控件外部
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    assert v != null;
                    KeyboardHelper.HideKeyboard(v);
                    if (editText != null) {
                        editText.clearFocus();
                    }
                }
            }
            return super.dispatchTouchEvent(ev);
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        return getWindow().superDispatchTouchEvent(ev) || onTouchEvent(ev);
    }

    public boolean isShouldHideInput(View v, MotionEvent event) {
        if (!(v == null) && (v instanceof EditText)) {
            editText = (EditText) v;
            int[] leftTop = {0, 0};
            // 获取输入框当前的location位置
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            return !(event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom);
        }
        return false;
    }

}
