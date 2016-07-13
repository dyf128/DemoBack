package com.dmeos.test.androidart.net.volley;

/**
 * 网络数据返回回调
 * @param <T>
 */
public interface ResponseCallback<T> {

    void onFailure(int code, String message);

    void onSuccess(int code, String message, T data);
}
