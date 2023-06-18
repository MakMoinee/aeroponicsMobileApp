package com.aeroponics.user.services;

import android.content.Context;

import com.aeroponics.user.activities.models.ServerRequestBody;
import com.aeroponics.user.interfaces.RequestListener;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class ServerRequest {
    Context mContext;
    public static final String PLANTS_PATH = "/plants";

    public ServerRequest(Context mContext) {
        this.mContext = mContext;
    }

    public void create(ServerRequestBody body, RequestListener listener) {
        if (body.getBody() == null) {
            listener.onError(new Error("empty body"));
            return;
        }
        if (body.getUrl() == null) {
            listener.onError(new Error("empty url"));
            return;
        }

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, body.getUrl(), body.getBody(), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if (response.length() > 0) {
                    listener.onSuccess(response);
                } else {
                    listener.onError(new Error("empty result"));
                }
            }
        }, error -> {
            try {
                listener.onError(new Error(error.getLocalizedMessage()));
            } catch (Exception e) {
                listener.onError(null);
            }
        });

        RequestQueue queue = Volley.newRequestQueue(mContext);
        queue.add(request);

    }

    public void delete(ServerRequestBody body, RequestListener listener) {
        if (body.getUrl() == null) {
            listener.onError(new Error("empty url"));
            return;
        }

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.DELETE, body.getUrl(), null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if (response.length() > 0) {
                    listener.onSuccess(response);
                } else {
                    listener.onError(new Error("empty result"));
                }
            }
        }, error -> {
            try {
                listener.onError(new Error(error.getLocalizedMessage()));
            } catch (Exception e) {
                listener.onError(null);
            }
        });

        RequestQueue queue = Volley.newRequestQueue(mContext);
        queue.add(request);
    }

    public void update(ServerRequestBody body, RequestListener listener) {
        if (body.getBody() == null) {
            listener.onError(new Error("empty body"));
            return;
        }
        if (body.getUrl() == null) {
            listener.onError(new Error("empty url"));
            return;
        }

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.PUT, body.getUrl(), body.getBody(), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if (response.length() > 0) {
                    listener.onSuccess(response);
                } else {
                    listener.onError(new Error("empty result"));
                }
            }
        }, error -> {
            try {
                listener.onError(new Error(error.getLocalizedMessage()));
            } catch (Exception e) {
                listener.onError(null);
            }
        });

        RequestQueue queue = Volley.newRequestQueue(mContext);
        queue.add(request);
    }

    public void getAll(ServerRequestBody body, RequestListener listener) {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, body.getUrl(), null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if (response.length() > 0) {
                    listener.onSuccess(response);
                } else {
                    listener.onError(new Error("empty result"));
                }
            }
        }, error -> {
            try {
                listener.onError(new Error(error.getLocalizedMessage()));
            } catch (Exception e) {
                listener.onError(null);
            }
        });

        RequestQueue queue = Volley.newRequestQueue(mContext);
        queue.add(request);
    }
}
