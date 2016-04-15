package com.dmeos.test.androidart.base;


public interface LoadDataView {
    void showProgress();

    void hideProgress();

    void onLoadDataFailure(int code, String message);

}
