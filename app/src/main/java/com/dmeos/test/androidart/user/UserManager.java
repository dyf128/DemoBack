package com.dmeos.test.androidart.user;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.dmeos.test.androidart.module.Result;
import com.dmeos.test.androidart.module.User;
import com.dmeos.test.androidart.net.GsonRequest;
import com.dmeos.test.androidart.net.RequestService;
import com.dmeos.test.androidart.net.ResponseCallback;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.Map;


public class UserManager {

    public void doLoginRequest(String username, String password, final ResponseCallback listener) {
        // TODO 发送网络请求 调用网络请求封装工具类 在工具类里头判断网络类型 是否联网等情况
        //Volley.newRequestQueue(AppApplication.getContext()).add();
        String url = "http://www.mocky.io/v2/5710ce62110000231f9e274a";
        Map<String, String> params = new HashMap<>();
        params.put("user_name", username);
        params.put("user_pwd", password);
        GsonRequest request = new GsonRequest<Result<User>>(url, new TypeToken<Result<User>>(){}.getType());
        Response.Listener lis = new Response.Listener<Result<User>>() {
            @Override
            public void onResponse(Result<User> response) {
                Log.d("HTTP", response.toString());
                listener.onSuccess(0, "success", response);
            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("HTTP", error.toString());
                int code = error.networkResponse.statusCode;
            }
        };
        request.setListener(lis);
        request.setErrorListener(errorListener);
        RequestService.addToQueue(request);
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
