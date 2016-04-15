package com.dmeos.test.androidart.net;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.dmeos.test.androidart.base.AppApplication;


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
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        req.setTag(tag);
        addToRequestQueue(req);
    }

}
