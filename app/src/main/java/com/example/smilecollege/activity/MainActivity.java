package com.example.smilecollege.activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.smilecollege.frament.DynamicFragment;
import com.example.smilecollege.frament.HomepgaeFragment;
import com.example.smilecollege.frament.NotificationsFragment;
import com.example.smilecollege.R;
import com.jaeger.library.StatusBarUtil;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    //    变量声明
    private Fragment fragment1;
    private Fragment fragment2;
    private Fragment fragment3;
    private Fragment[] fragments;
    private int lastfragment;
    private Toolbar toolbar;
    private long mExitTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.search_toolbar);
        toolbar.setTitle(R.string.title_home);
//        将原来的替换掉
        setSupportActionBar(toolbar);
//        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);

        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

//        为左侧滑栏中的按钮添加监听器
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

//        底部按钮添加监听器
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        findViewById(R.id.search_bar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                //启动
                startActivity(intent);
            }
        });

//        初始化话页面
        initFragment();
        StatusBarUtil.setTransparent(this);
    }

    //声明一个long类型变量mExitTime;：用于存放上一点击“返回键”的时刻，重写返回按钮的监听器
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //判断用户是否点击了“返回键”
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //与上次点击返回键时刻作差
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                //大于2000ms则认为是误操作，使用Toast进行提示
                Toast.makeText(this, "再按一次退出程序哦～", Toast.LENGTH_SHORT).show();
                //并记录下本次点击“返回键”的时刻，以便下次进行判断
                mExitTime = System.currentTimeMillis();
            } else {
                //小于2000ms则认为是用户确实希望退出程序-调用System.exit()方法进行退出
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

//    添加右上角菜单
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // 左侧菜单栏的点击响应动作
        int id = item.getItemId();

        if (id == R.id.nav_homepage) {

        } else if (id == R.id.nav_topic) {

        }else if (id == R.id.nav_collection) {

        }else if (id == R.id.nav_history) {

        }else if (id == R.id.nav_theme) {

        }else if (id == R.id.nav_setting) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //    初始化碎片
    private void initFragment()
    {
        fragment1 = new HomepgaeFragment();
        fragment2 = new DynamicFragment();
        fragment3 = new NotificationsFragment();
        fragments = new Fragment[]{fragment1,fragment2,fragment3};
        lastfragment=0;
        getSupportFragmentManager().beginTransaction().replace(R.id.main_view,fragment1).show(fragment1).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                {
                    if(lastfragment!=0)
                    {
                        toolbar.setTitle(R.string.title_home);
                        switchFragment(lastfragment,0);
                        lastfragment=0;
                    }
                    return true;
                }
                case R.id.navigation_dynamic:
                {
                    if(lastfragment!=1)
                    {
                        toolbar.setTitle(R.string.title_follower);
                        switchFragment(lastfragment,1);
                        lastfragment=1;
                    }
                    return true;
                }
                case R.id.navigation_notifications:
                {
                    if(lastfragment!=2)
                    {
                        toolbar.setTitle(R.string.title_notifications);
                        switchFragment(lastfragment,2);
                        lastfragment=2;
                    }
                    return true;
                }
            }
            return false;
        }
    };

    private void switchFragment(int lastfragment,int index)
    {
        FragmentTransaction transaction =getSupportFragmentManager().beginTransaction();
        transaction.hide(fragments[lastfragment]);//隐藏上个Fragment
        if(fragments[index].isAdded()==false)
        {
            transaction.add(R.id.main_view,fragments[index]);
        }
        transaction.show(fragments[index]).commitAllowingStateLoss();

    }

}
