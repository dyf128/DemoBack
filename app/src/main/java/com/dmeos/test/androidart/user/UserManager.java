package com.dmeos.test.androidart.user;

import com.dmeos.test.androidart.module.User;
import com.dmeos.test.androidart.net.JsonObjectRequest;
import com.dmeos.test.androidart.net.ResponseCallback;

import java.util.HashMap;
import java.util.Map;


public class UserManager {

    public void doLoginRequest(String username, String password, ResponseCallback listener, String requestTag) {
        // TODO 发送网络请求 调用网络请求封装工具类 在工具类里头判断网络类型 是否联网等情况
        String url = "http://www.mocky.io/v2/571443950f0000d3064904b5";
        Map<String, String> params = new HashMap<>();
        params.put("user_name", username);
        params.put("user_pwd", password);
//        GsonRequest request = new GsonRequest<Result<User>>(url, new TypeToken<Result<User>>() {
//        }.getType(), listener);
        JsonObjectRequest request = new JsonObjectRequest(url, listener);
        request.setPostParams(params);
        request.setTag(requestTag);
        request.dorequest();
    }

    public void getUserInfoById(String id, ResponseCallback listener) {
        // TODO 网络请求用户信息
    }

    public void doSaveUserInfo(User user) {
        // TODO 保存登录信息到本地SharePref 或者 在调用一个封装的Dao接口 保存到数据库
        if (user != null) {

        }
    }
}
