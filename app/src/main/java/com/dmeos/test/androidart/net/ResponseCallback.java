package com.dmeos.test.androidart.net;

public interface ResponseCallback<T> {

    void onFailure(int code, String message);

    void onSuccess(int code, String message, T data);
}
