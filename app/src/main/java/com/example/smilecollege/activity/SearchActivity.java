package com.example.smilecollege.activity;

import android.content.Context;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.example.smilecollege.R;

public class SearchActivity extends AppCompatActivity {

    private static boolean Boolean_showInputMethod;
    private static EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_layout);
        Boolean_showInputMethod = true;


//        手动设置状态栏颜色
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }

//        按取消按钮之后结束当前活动，即返回上一级
        findViewById(R.id.search_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HideKeyboard(v);
                SearchActivity.this.finish();
            }
        });

        editText = (EditText)findViewById(R.id.search_edit);
//        点击搜索栏自动呼出输入法
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = SearchActivity.this;
                showInputMethod(context,v);
            }
        });

//        editText = findViewById(R.id.search_edit);
//        editText.clearFocus();

    }

    @Override
    protected void onStart() {
        super.onStart();
//        默认一跳转到此界面就呼出键盘
        editText.performClick();
    }

    //    重写返回的方法，若键盘显示则关闭键盘，若无则返回上级
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if(Boolean_showInputMethod) {
                View v = getWindow().getDecorView();
                editText.clearFocus();
                HideKeyboard(v);
            }else {
                SearchActivity.this.finish();
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    //    显示键盘
    public static void showInputMethod(Context context, View view) {
        InputMethodManager im = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        im.showSoftInput(view, 0);
        Boolean_showInputMethod = true;
    }
//    隐藏虚拟键盘
    public static void HideKeyboard(View v){
        InputMethodManager imm = ( InputMethodManager) v.getContext( ).getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            imm.hideSoftInputFromWindow( v.getApplicationWindowToken() , 0 );
        }
        Boolean_showInputMethod = false;
    }


    //使editText点击外部时候失去焦点
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideInput(v, ev)) {//点击editText控件外部
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    assert v != null;
//                    hideInput();
                    HideKeyboard(v);
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
        if (v != null && (v instanceof EditText)) {
            editText = (EditText) v;
            int[] leftTop = {0, 0};
            //获取输入框当前的location位置
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
