package com.dmeos.test.androidart.user;

import com.dmeos.test.androidart.module.User;


public interface IUserPresenter {

    /**
     * 执行登录 发送请求
     * @param userName
     * @param password
     */
    void doLogin(String userName, String password);


    /**
     * 通过id获取用户信息
     * @param userId
     */
    void getUserInfoById(String userId);


    /**
     * 保存用户信息
     */
    void saveLoginUserInfo(User user);
}
