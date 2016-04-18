package com.dmeos.test.androidart.net;


import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
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
        JSONObject jsonObject = null;
        try {
            String json = new String(response.data,
                    HttpHeaderParser.parseCharset(response.headers));
            jsonObject = new JSONObject(json);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            Response.error(new ParseError());
        } catch (JSONException e) {
            e.printStackTrace();
            Response.error(new ParseError());
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
            NetworkResponse response = error.networkResponse;
            if (response != null) {
                responseCallback.onFailure(response.statusCode, NetUtil.httpRequestErrorMsg(response.statusCode));
            }
        }
    };


}
