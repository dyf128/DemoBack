package com.dmeos.test.androidart.net;


import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.dmeos.test.androidart.module.Result;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;

public class GsonRequest<T extends Result> extends AbstractRequest<T> {
    private static Gson mGson = new Gson();

    private final Gson gson = new Gson();
    private Type type;

    public GsonRequest(String url, Type type) {
        super(url);
        this.type = type;
    }

    public GsonRequest setType(Type type) {
        this.type = type;
        return this;
    }
    public Type getType() {
        return type;
    }


    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        String json = null;
        try {
            json = new String(response.data,
                    HttpHeaderParser.parseCharset(response.headers));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return (Response<T>) Response.success(
                (T)(gson.fromJson(json, getType())),
                HttpHeaderParser.parseCacheHeaders(response));
    }

}
