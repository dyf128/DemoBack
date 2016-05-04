package com.dmeos.test.androidart.base;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

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
     *
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

    public void showToast(String message) {
        if (!TextUtils.isEmpty(message)) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
    }

    public void showToast(String message, int time) {
        if (!TextUtils.isEmpty(message)) {
            Toast.makeText(this, message, time).show();
        }
    }

    /**
     * 加载数据显示titlebar loading圈
     */
    public void showTitlebarLoading() {

    }

    /**
     * 加载数据隐藏titlebar loading圈
     */
    public void hiddenTitlebarLoading() {

    }

    /**
     * 加载数据显示Loading dialog
     */
    public void showBlockLoadingDailogView() {
        
    }

    /**
     * 加载数据显示Loading dialog
     */
    public void dismissBlockLoadingDailogView() {

    }

}
