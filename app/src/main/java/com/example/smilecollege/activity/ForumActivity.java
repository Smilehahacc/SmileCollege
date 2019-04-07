package com.example.smilecollege.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.smilecollege.R;
import com.example.smilecollege.base.BaseActivity;
import com.example.smilecollege.fragment.RecyclerViewFragment;
import com.github.florent37.materialviewpager.MaterialViewPager;
import com.github.florent37.materialviewpager.header.HeaderDesign;

public class ForumActivity extends BaseActivity {
    private MaterialViewPager mViewPager;
    static final int TAPS = 8;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum);
        mViewPager = findViewById(R.id.materialViewPager);

        //添加监听
        mViewPager.setMaterialViewPagerListener(new MaterialViewPager.Listener() {
            @Override
            public HeaderDesign getHeaderDesign(int page) {
                switch (page) {
                    case 0:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.cyan,
                                "https://fs01.androidpit.info/a/63/0e/android-l-wallpapers-630ea6-h900.jpg");
                    case 1:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.green_teal,
                                "https://fs01.androidpit.info/a/63/0e/android-l-wallpapers-630ea6-h900.jpg");
                    case 2:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.pink,
                                "http://www.droid-life.com/wp-content/uploads/2014/10/lollipop-wallpapers10.jpg");
                    case 3:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.purple_clove,
                                "http://www.tothemobile.com/wp-content/uploads/2014/07/original.jpg");
                    case 4:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.colorAccent,
                                "http://www.tothemobile.com/wp-content/uploads/2014/07/original.jpg");
                    case 5:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.blue_sapphire,
                                "http://www.tothemobile.com/wp-content/uploads/2014/07/original.jpg");
                    case 6:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.orange_yellow,
                                "http://www.tothemobile.com/wp-content/uploads/2014/07/original.jpg");
                    case 7:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.green,
                                "http://www.tothemobile.com/wp-content/uploads/2014/07/original.jpg");
                }

                //execute others actions if needed (ex : modify your header logo)

                return null;
            }
        });
        //设置Toolbar
        Toolbar toolbar = mViewPager.getToolbar();

        if (toolbar != null) {
            setSupportActionBar(toolbar);

            // 设置常用状态栏控件是否显示,是否可用
            ActionBar actionBar = getSupportActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayUseLogoEnabled(false);
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        //设置Adapter
        mViewPager.getViewPager().setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {

            @Override
            public Fragment getItem(int position) {
                switch (position % TAPS) {
                    case 0:
                        return RecyclerViewFragment.newInstance();

                    default:
                        return RecyclerViewFragment.newInstance();
                }
            }

            @Override
            public int getCount() {
                return TAPS;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                switch (position % TAPS) {
                    case 0:
                        return " 与你一起 ";
                    case 1:
                        return " 乐享校园 ";
                    case 2:
                        return " 校园帮帮 ";
                    case 3:
                        return " 团团纳新 ";
                    case 4:
                        return " 学习交流 ";
                    case 5:
                        return " 你我易起 ";
                    case 6:
                        return " 寻物启事 ";
                    case 7:
                        return " 兼职招聘 ";
                    default:
                        return " 暂未分类 ";
                }
            }
        });
        //设置setViewPager
        mViewPager.getViewPager().setOffscreenPageLimit(mViewPager.getViewPager().getAdapter().getCount());
        mViewPager.getPagerTitleStrip().setViewPager(mViewPager.getViewPager());
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
