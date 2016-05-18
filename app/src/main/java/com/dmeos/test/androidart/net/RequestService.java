package com.dmeos.test.androidart.net;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.dmeos.test.androidart.R;
import com.dmeos.test.androidart.base.AppApplication;
import com.dmeos.test.androidart.utils.Constants;

import java.io.InputStream;

/**
 * 所有请求通过该类去加入请求队列执行|取消
 */
public class RequestService {

    private RequestQueue mRequestQueue;
    private static RequestService instance;
    private Context mContext;

    private RequestService(Context context) {
        this.mContext = context;
    }

    public synchronized static RequestService getInstance() {
        if (instance == null)
            instance = new RequestService(AppApplication.getContext());
        return instance;
    }

    private synchronized RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(mContext);
        }
        return mRequestQueue;
    }

    private RequestQueue getSSLRequestQueue() {
        if (mRequestQueue == null) {
            synchronized (RequestService.class) {
                if (mRequestQueue == null) {
                    // Replace R.raw.test with your keystore
                    Context application = mContext;
                    InputStream keyStore = application.getResources().openRawResource(R.raw.test);
                    mRequestQueue = Volley.newRequestQueue(application,
                            new ExtHttpClientStack(new SslHttpClient(keyStore, Constants.SSL_INFO_PWD, Constants.SSL_INFO_PORT)));
                }
            }
        }
        return mRequestQueue;
    }


    public static void cancel(Object tag) {
        if (tag == null) {
            return;
        }
        getInstance().getRequestQueue().cancelAll(tag);
    }

    public static void addToQueue(Request request) {
        getInstance().getRequestQueue().add(request);
    }

    public static void addToQueue(Request request, String tag) {
        request.setTag(tag);
        addToQueue(request);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getInstance().getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        req.setTag(tag);
        addToRequestQueue(req);
    }


    public <T> void addToRequestQueue(Request<T> req, boolean isSSLRequest) {
        if (isSSLRequest) {
            getSSLRequestQueue().add(req);
        } else {
            getRequestQueue().add(req);
        }
    }

    public <T> void addToRequestQueue(Request<T> req, String tag,  boolean isSSLRequest) {
        req.setTag(tag);
        addToRequestQueue(req, isSSLRequest);
    }


}
