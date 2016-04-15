package com.dmeos.test.androidart.home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class MainFragmentPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments = new ArrayList<>();


    public MainFragmentPagerAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        this.fragments = fragmentList;
    }

    // 主要实现这两个方法
    @Override
    public Fragment getItem(int position) {
        if (position < fragments.size()) {
            return fragments.get(position);
        }
        return null;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // 防止销毁fragment
        super.destroyItem(container, position, object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }

    @Override
    public int getCount() {
        if (fragments != null && fragments.size() > 0){
            return  fragments.size();
        }
        return 0;
    }
}
