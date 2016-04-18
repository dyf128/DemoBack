package com.dmeos.test.androidart.base;

/**
 * 所有需要加载数据的页面(Activity|Fragment)实现该接口 具体的业务可通过继承该接口后实现子类
 */
public interface LoadDataView {
    void showProgress();

    void hideProgress();

    void onLoadDataFailure(int code, String message);

}
