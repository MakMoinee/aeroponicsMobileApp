package com.aeroponics.user.activities.models;

import com.android.volley.toolbox.JsonObjectRequest;


import org.json.JSONObject;

import lombok.Data;

@Data
public class ServerRequestBody {
    private String url;
    private JSONObject body;

    public ServerRequestBody() {
    }

    public ServerRequestBody(ServerRequestBodyBuilder builder) {
        this.url = builder.url;
        this.body = builder.body;
    }

    public static class ServerRequestBodyBuilder {
        private String url;
        private JSONObject body;

        public ServerRequestBodyBuilder() {
        }

        public ServerRequestBodyBuilder setUrl(String url) {
            this.url = url;
            return this;
        }

        public ServerRequestBodyBuilder setBody(JSONObject body) {
            this.body = body;
            return this;
        }

        public ServerRequestBody build() {
            return new ServerRequestBody(this);
        }
    }
}
