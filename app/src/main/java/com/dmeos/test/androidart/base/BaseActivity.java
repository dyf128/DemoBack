package com.dmeos.test.androidart.base;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.dmeos.test.androidart.R;
import com.dmeos.test.androidart.widget.TitleBar;

public abstract class BaseActivity extends FragmentActivity {
    private static final String TAG = BaseActivity.class.getSimpleName();


    private TitleBar mTitleBar;
    private View mBaseContainerView;
    private View mContentChildView;
    private ViewGroup mContent;

    /**
     * 子类通过该方法加载自己的layout
     */
    protected abstract void initBaseView();

    /**
     * 子类在这个方法里初始化自己的控件
     */
    protected abstract void initView();

    /**
     * 获得Titlebar
     * @return
     */
    public TitleBar getTitlebar() {
        return mTitleBar;
    }

    public String getResStringById(int id) {
        return getResources().getString(id);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, this.getClass().getSimpleName());
        initBaseView();
        initView();
    }

    @Override
    public void setContentView(int layoutResID) {
        mBaseContainerView = LayoutInflater.from(this).inflate(R.layout.base_view, null);
        mContentChildView = LayoutInflater.from(this).inflate(layoutResID, null);
        mTitleBar = (TitleBar) mBaseContainerView.findViewById(R.id.titlebar);
        mContent = (ViewGroup) mBaseContainerView.findViewById(R.id.base_content);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        mContentChildView.setLayoutParams(params);
        mContent.addView(mContentChildView);
        mTitleBar.setmCurrentActvity(this);
        super.setContentView(mBaseContainerView);
    }

    /**
     * 带Loading框的加载数据
     */
    public void loadDataWidthLoadingDialog() {

    }

    /**
     * 不带Loading框的加载数据
     */
    public void loadDataWidthoutLoadingDialog() {

    }

    public void showBlockLoadingDailogView(){

    }

    public void hiddenBlockLoadingDailogView(){

    }

    public void showLodaingTitleBarView(){

    }
    public void hiddenLodaingTitleBarView(){

    }
}
