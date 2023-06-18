package com.aeroponics.user.common;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONObject;

import java.util.Map;

public class MapForm {
    public static Map<String, Object> convertObjectToMap(Object data) {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> param = mapper.convertValue(data, Map.class);
        return param;
    }

    public static JSONObject convertObjectToJSONObjectRequest(Object data) {
        ObjectMapper mapper = new ObjectMapper();
        JSONObject request = mapper.convertValue(data, JSONObject.class);
        return request;
    }
}
