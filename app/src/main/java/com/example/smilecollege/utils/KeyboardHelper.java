package com.example.smilecollege.utils;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;


public class KeyboardHelper {

    static KeyboardHelper instance;

    public KeyboardHelper(){}

    public static KeyboardHelper getInstance() {
        if (instance == null) {
            instance = new KeyboardHelper();
        }
        return instance;
    }

    //    显示键盘
    public static void showInputMethod(Context context, View view) {
        InputMethodManager im = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        im.showSoftInput(view, 0);

    }
    //    隐藏虚拟键盘
    public static void HideKeyboard(View v){
        InputMethodManager imm = ( InputMethodManager) v.getContext( ).getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            imm.hideSoftInputFromWindow( v.getApplicationWindowToken() , 0 );
        }
    }

}
