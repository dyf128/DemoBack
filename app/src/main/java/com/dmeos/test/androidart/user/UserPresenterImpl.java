package com.dmeos.test.androidart.user;

import com.dmeos.test.androidart.module.Result;
import com.dmeos.test.androidart.module.User;
import com.dmeos.test.androidart.net.volley.ResponseCallback;


public class UserPresenterImpl implements IUserPresenter, ResponseCallback {

    private IUserView loginView;
    private UserManager loginManager;

    public UserPresenterImpl(IUserView loginView) {
        this.loginView = loginView;
        this.loginManager = new UserManager();
    }

    @Override
    public void doLogin(String userName, String password, String requestTag) {
        loginView.showProgress();
        loginManager.doLoginRequest(userName, password, this, requestTag);
    }


    @Override
    public void getUserInfoById(String userId) {
        loginView.showProgress();
        loginManager.getUserInfoById(userId, this);
    }

    @Override
    public void saveLoginUserInfo(User user) {
        loginManager.doSaveUserInfo(user);
    }

    @Override
    public void onFailure(int code, String message) {
        loginView.hideProgress();
        loginView.onLoadDataFailure(code, message);
    }

    @Override
    public void onSuccess(int code, String message, Object data) {
        loginView.hideProgress();
        if (data instanceof Result) {
            loginView.onLoadUserDataSuccess(code, message, (User)((Result) data).data);
        } else {
            loginView.onLoadUserDataSuccess(code, message, null);
        }
    }


}
