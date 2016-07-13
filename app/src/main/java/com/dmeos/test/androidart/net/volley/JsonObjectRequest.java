package com.dmeos.test.androidart.net.volley;


import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.dmeos.test.androidart.utils.Constants;
import com.dmeos.test.androidart.utils.LogUtils;
import com.dmeos.test.androidart.utils.NetUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class JsonObjectRequest extends AbstractRequest<JSONObject> {
    private ResponseCallback responseCallback;

    public JsonObjectRequest(String url, ResponseCallback callback) {
        super(url);
        this.responseCallback = callback;
        setListener(listener);
        setErrorListener(errorListener);
    }

    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
        JSONObject jsonObject;
        try {
            String json = new String(response.data,
                    HttpHeaderParser.parseCharset(response.headers));
            jsonObject = new JSONObject(json);
            if (Constants.IS_DEBUG) {
                LogUtils.i(json);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return Response.error(new ParseError(e));
        } catch (JSONException e) {
            e.printStackTrace();
            return Response.error(new ParseError(e));
        }
        return Response.success(jsonObject,
                HttpHeaderParser.parseCacheHeaders(response));
    }

    private Response.Listener listener = new Response.Listener() {
        @Override
        public void onResponse(Object response) {
            if (response instanceof JSONObject) {
                responseCallback.onSuccess(0, "success", response);
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
