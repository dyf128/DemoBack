package com.dmeos.test.androidart.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.dmeos.test.androidart.R;
import com.dmeos.test.androidart.base.BaseActivity;
import com.dmeos.test.androidart.widget.BottomTab;
import com.dmeos.test.androidart.widget.BottomTabGroup;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    private HorizontalDisableViewPager mViewPager;
    private List<Fragment> fragments;
    private BottomTabGroup mBottomTabGroup;
    private int mCurrentTab = 0;


    @Override
    protected void initBaseView() {
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initView() {
        fragments = new ArrayList<>();
        fragments.add(new FirstFragment());
        fragments.add(new SecondFragment());
        fragments.add(new ThirdFragment());
        mViewPager = (HorizontalDisableViewPager) findViewById(R.id.main_pageview);
        mBottomTabGroup = (BottomTabGroup) findViewById(R.id.bottom_bar_root);
        mViewPager.setAdapter(new MainFragmentPagerAdapter(getSupportFragmentManager(), fragments));
        mViewPager.setOffscreenPageLimit(fragments.size());
        mViewPager.setCurrentItem(mCurrentTab, false);
        BottomTab tab = (BottomTab)mBottomTabGroup.getChildAt(mCurrentTab);
        tab.setChecked(true);
        mBottomTabGroup.setOnCheckedChangeListener(new BottomTabGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(BottomTabGroup root, int checkedId) {
                switch (checkedId) {
                    case R.id.tab_01:
                        mViewPager.setCurrentItem(0,false);
                        mCurrentTab = 0;
                        break;
                    case R.id.tab_02:
                        mViewPager.setCurrentItem(1,false);
                        mCurrentTab = 1;
                        break;
                    case R.id.tab_03:
                        mViewPager.setCurrentItem(2,false);
                        mCurrentTab = 2;
                        break;
                }
            }
        });
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

}
