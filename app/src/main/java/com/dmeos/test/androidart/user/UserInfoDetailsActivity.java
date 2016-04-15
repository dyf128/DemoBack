package com.dmeos.test.androidart.user;

import android.content.Intent;
import android.os.Bundle;

import com.dmeos.test.androidart.R;
import com.dmeos.test.androidart.base.BaseActivity;
import com.dmeos.test.androidart.module.User;
import com.dmeos.test.androidart.utils.Constants;

public class UserInfoDetailsActivity extends BaseActivity implements IUserView
{
    private String mUserId;
    private IUserPresenter mLoginPresenter;
    private User mUser = null;

    @Override
    protected void initBaseView() {
        setContentView(R.layout.activity_user_info_details);
    }

    @Override
    protected void initView() {
        mLoginPresenter.getUserInfoById(mUserId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        mUserId = intent.getStringExtra(Constants.INTENT_DATA_USER_ID);
        mLoginPresenter = new UserPresenterImpl(this);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void onLoadDataFailure(int code, String message) {

    }

    @Override
    public void onLoadUserDatauccess(int code, String message, User user) {
        // TODO 填充页面数据
        mUser = user;
        updateUserView(mUser);
    }

    private void updateUserView(User user){
        if (user == null) {
            return;
        }
    }

}
