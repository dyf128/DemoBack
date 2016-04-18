package com.dmeos.test.androidart.net;


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
    private ResponseCallback<T> httpListener;

    public AbstractRequest(String url) {
        super(Method.GET, url, null);
        this.url = url;
    }

    public ResponseCallback<T> getHttpListener() {
        return httpListener;
    }

    public AbstractRequest setHttpListener(ResponseCallback<T> httpListener) {
        this.httpListener = httpListener;
        return this;
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

    public Map<String, String> getParams() {
        return postParams;
    }

    @Override
    public Map<String, String> getHeaders() {
        Map<String, String> generalHeaders = new HashMap<>();
        return generalHeaders;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public Response.ErrorListener getErrorListener() {
        return errorListener;
    }


    @Override
    public void deliverError(VolleyError error) {
        int errorCode = -1;
        if (error.networkResponse != null) {
            errorCode =  error.networkResponse.statusCode;
        }
        if (httpListener != null) {
            httpListener.onFailure(errorCode, error.getMessage());
        }
        if (errorListener != null) {
            errorListener.onErrorResponse(error);
        }
    }


    @Override
    protected void deliverResponse(T response) {
        if (listener != null) {
            listener.onResponse(response);
        }
        if (httpListener != null) {
            httpListener.onSuccess(0,"success", response);
        }
    }

    public void doequest() {
        RequestService.addToQueue(this);
    }
}
