package com.dmeos.test.androidart.user;

import com.dmeos.test.androidart.module.User;
import com.dmeos.test.androidart.base.LoadDataView;


public interface IUserView extends LoadDataView {

    void onLoadUserDataSuccess(int code, String message, User user);
}
