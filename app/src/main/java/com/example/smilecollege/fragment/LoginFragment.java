package com.example.smilecollege.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smilecollege.R;
import com.example.smilecollege.activity.LoginActivity;
import com.example.smilecollege.activity.MainActivity;
import com.example.smilecollege.base.BaseFragment;
import com.example.smilecollege.utils.HttpUtils;

import java.io.IOException;

public class LoginFragment extends BaseFragment {

    private View view;
    private Toolbar toolbar;
    TextView textView;
    private LoginActivity loginActivity;
    private KeyEvent event;
    private Button buttonBack;

    // 一些控件的定义，请求头设定等
    private static final String TAG ="LoginActivity";
    private EditText EtName;
    private EditText EtPassword;
    private Button buttonLogin;
    private Boolean loginResult;
    private String url ="http://qvchqa.natappfree.cc/login";

    static LoginFragment instance;
    public static LoginFragment getInstance() {
        if (instance == null) {
            instance = new LoginFragment();
        }
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_login,container,false);
        toolbar = (Toolbar)view.findViewById(R.id.toolbar_base);
        setHasOptionsMenu(true);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        textView = view.findViewById(R.id.toolbar_base_title);
        textView.setText(R.string.title_login);

        // 添加监听器
        initView(view,savedInstanceState);
        addListener();
        return view;
    }

    protected void addListener() {

        // 点击返回键回到登录-注册主界面
        buttonBack = (Button)view.findViewById(R.id.button_back);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginActivity = (LoginActivity) getActivity();
                loginActivity.onKeyDown(KeyEvent.KEYCODE_BACK,null);
            }
        });

        // 点击登录按钮之后验证登录
        buttonLogin = (Button)view.findViewById(R.id.login);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
                if(loginResult) {
                    Toast.makeText(getActivity().getApplicationContext(), "登录成功！", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getActivity().getApplicationContext(), "登录失败，请检查帐号或密码！", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // 初始化控件
    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        EtName = (EditText) view.findViewById(R.id.username);
        EtPassword = (EditText) view.findViewById(R.id.password);
        buttonBack = (Button)view.findViewById(R.id.button_back);
        buttonLogin = (Button)view.findViewById(R.id.login);
        loginResult = false;
    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    /*
    登录
     */
    private void login() {
        final String name = EtName.getText().toString().trim();
        final String password = EtPassword.getText().toString().trim();

        if(TextUtils.isEmpty(name) || TextUtils.isEmpty(password)){
            Toast.makeText(getActivity().getApplicationContext(), "用户名或者密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        new Thread(){
            @Override
            public void run() {
                HttpUtils httpUtils = new HttpUtils();
                //转换为JSON
                String user = httpUtils.bolwingJson(name, password);

                Log.d(TAG, "user:" + user);
                try {
                    final String result = httpUtils.login(url, user);
                    Log.d(TAG, "登录验证的结果:" + result);
                    //更新UI,在UI线程中
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if("SUCCESS".equals(result)){
                                loginResult = true;
                            }else{
                                loginResult = false;
                            }
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

}
