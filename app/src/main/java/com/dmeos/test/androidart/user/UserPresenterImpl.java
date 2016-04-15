package com.dmeos.test.androidart.user;

import com.dmeos.test.androidart.module.Result;
import com.dmeos.test.androidart.module.User;
import com.dmeos.test.androidart.net.ResponseCallback;


public class UserPresenterImpl implements IUserPresenter, ResponseCallback {

    private IUserView loginView;
    private UserManager loginManager;

    public UserPresenterImpl(IUserView loginView) {
        this.loginView = loginView;
        this.loginManager = new UserManager();
    }

    @Override
    public void doLogin(String userName, String password) {
        loginView.showProgress();
        loginManager.doLoginRequest(userName, password, this);
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
        if (data instanceof Result) {
            loginView.onLoadUserDatauccess(code, message, (User)((Result) data).data);
        } else {
            loginView.onLoadUserDatauccess(code, message, null);
        }
    }


}
