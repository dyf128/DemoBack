package com.dmeos.test.androidart.net;


import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.dmeos.test.androidart.module.Result;
import com.dmeos.test.androidart.utils.Constants;
import com.dmeos.test.androidart.utils.LogUtils;
import com.dmeos.test.androidart.utils.NetUtil;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;

public class GsonRequest<T extends Result> extends AbstractRequest<T> {
    private final Gson gson = new Gson();
    private Type type;
    private ResponseCallback responseCallback;

    public GsonRequest(String url, Type type, ResponseCallback callback) {
        super(url);
        this.type = type;
        this.responseCallback = callback;
        setListener(listener);
        setErrorListener(errorListener);
    }

    public Type getType() {
        return type;
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        String json;
        try {
            json = new String(response.data,
                    HttpHeaderParser.parseCharset(response.headers));
            if (Constants.IS_DEBUG) {
                LogUtils.i(json);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return Response.error(new ParseError(e));
        }
        return (Response<T>) Response.success(
                (T) (gson.fromJson(json, getType())),
                HttpHeaderParser.parseCacheHeaders(response));
    }

    private Response.Listener listener = new Response.Listener() {
        @Override
        public void onResponse(Object response) {
            /* 如果需要 可在此处理自己相关业务的result code */
            if (response instanceof Result) {
                int resultCode = ((Result) response).code;
                String resultMessage = ((Result) response).message;
                responseCallback.onSuccess(resultCode, resultMessage, response);
            }
        }
    };

    private Response.ErrorListener errorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            /* 处理请求异常信息 */
            if (error == null) {
                responseCallback.onFailure(-1, Constants.NET_REQUEST_ERROR_MSG_UNKNOW);
                return;
            }
            NetworkResponse response = error.networkResponse;
            if (response == null) {
                responseCallback.onFailure(-1,  NetUtil.httpVolleyExceptionInfo(error));
                return;
            }
            responseCallback.onFailure(response.statusCode, NetUtil.httpResponseErrorMsg(response.statusCode));
        }
    };


}
