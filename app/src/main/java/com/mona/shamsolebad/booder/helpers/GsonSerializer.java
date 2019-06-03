package com.mona.shamsolebad.booder.helpers;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;



public class GsonSerializer {

    public static JSONObject serialize(Object obj) throws JSONException {

        Gson gson = new Gson();
        String jsonString = gson.toJson(obj);
        return new JSONObject(jsonString);
    }

    public static <T> T deserialize(JSONObject json, Class<T> classOfT) throws JSONException {

        Gson gson = new Gson();
        return gson.fromJson(json.toString(), classOfT);
    }
}
