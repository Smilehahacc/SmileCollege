package com.example.smilecollege.frament;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.smilecollege.R;
import com.example.smilecollege.base.BaseFragment;

import java.io.File;

public class LoginFragment extends BaseFragment {

    static LoginFragment instance;
    public static LoginFragment getInstance() {
        if (instance == null) {
            instance = new LoginFragment();
        }
        return instance;
    }

    private Toolbar toolbar;
    TextView textView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_login,container,false);
        view.setFocusable(true);//这个和下面的这个命令必须要设置了，才能监听back事件。
        view.setFocusableInTouchMode(true);
        view.setOnKeyListener(backlistener);
        toolbar = (Toolbar)view.findViewById(R.id.toolbar_base);
        setHasOptionsMenu(true);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        textView = view.findViewById(R.id.toolbar_base_title);
        textView.setText(R.string.title_login);
        return view;
    }

    private View.OnKeyListener backlistener = new View.OnKeyListener() {
        @Override
        public boolean onKey(View view, int i, KeyEvent keyEvent) {
            if (keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                // 表示按返回键 时的操作
                if (i == KeyEvent.KEYCODE_BACK) {
                        return true;
                    }
            }
            return false;
        }
    };

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

}
