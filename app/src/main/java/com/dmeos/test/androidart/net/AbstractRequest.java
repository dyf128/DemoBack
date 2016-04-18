package com.dmeos.test.androidart.net;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;

import java.util.HashMap;
import java.util.Map;


public abstract class AbstractRequest<T> extends Request<T> {
    private int requestMethod = Method.GET;
    private String url;
    private Map<String, String> postParams;
    private Map<String, String> headers;
    private Response.Listener<T> listener;
    private Response.ErrorListener errorListener;

    public AbstractRequest(String url) {
        super(Method.GET, url, null);
        this.url = url;
    }

    public AbstractRequest setRequestMethod(int requestMethod) {
        this.requestMethod = requestMethod;
        return this;
    }

    public AbstractRequest setPostParams(Map<String, String> params) {
        this.requestMethod = Method.POST;
        this.postParams = params;
        return this;
    }

    public AbstractRequest addPostParam(String key, String value) {
        this.requestMethod = Method.POST;
        if (postParams == null) {
            postParams = new HashMap<>();
        }
        postParams.put(key, value);
        return this;
    }

    public AbstractRequest addPostParams(Map<String, String> params) {
        this.requestMethod = Method.POST;
        if (postParams == null) {
            postParams = new HashMap<>();
        }
        postParams.putAll(params);
        return this;
    }

    public AbstractRequest setHeaders(Map<String, String> headers) {
        this.headers = headers;
        return this;
    }

    public AbstractRequest setListener(Response.Listener<T> listener) {
        this.listener = listener;
        return this;
    }

    public AbstractRequest setErrorListener(Response.ErrorListener listener) {
        this.errorListener = listener;
        return this;
    }

    public AbstractRequest setUrl(String url) {
        this.url = url;
        return this;
    }

    @Override
    public AbstractRequest setRetryPolicy(RetryPolicy retryPolicy) {
        super.setRetryPolicy(retryPolicy);
        return this;
    }

    @Override
    public AbstractRequest<?> setTag(Object tag) {
        super.setTag(tag);
        return this;
    }

    @Override
    public int getMethod() {
        return requestMethod;
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return postParams;
    }

    @Override
    public Map<String, String> getHeaders() {
        Map<String, String> generalHeaders = new HashMap<>();
        generalHeaders.put("platform","Android");
        if (headers != null) {
            headers.putAll(generalHeaders);
        } else {
            headers = generalHeaders;
        }
        return headers;
    }

    @Override
    public String getUrl() {
        return url;
    }


    @Override
    public void deliverError(VolleyError error) {
        if (errorListener != null) {
            errorListener.onErrorResponse(error);
        }
    }


    @Override
    protected void deliverResponse(T response) {
        if (listener != null) {
            listener.onResponse(response);
        }
    }

    public void doequest() {
        RequestService.addToQueue(this);
    }
}
