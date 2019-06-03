package com.mona.shamsolebad.booder.helpers;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

/**
 * Created by ehsan on 12/23/16.
 */

public class RequestQueueProvider {

    private static RequestQueueProvider mInstance;
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;
    private Context activity;

    private RequestQueueProvider(Context activity) {

        this.activity = activity;
        mRequestQueue = getRequestQueue();

        mImageLoader = new ImageLoader(mRequestQueue,
                new ImageLoader.ImageCache() {
                    private final LruCache<String, Bitmap>
                            cache = new LruCache<String, Bitmap>(20);

                    @Override
                    public Bitmap getBitmap(String url) {
                        return cache.get(url);
                    }

                    @Override
                    public void putBitmap(String url, Bitmap bitmap) {
                        cache.put(url, bitmap);
                    }
                });
    }

    public static synchronized RequestQueueProvider getInstance(Context activity) {

        if (mInstance == null) {
            mInstance = new RequestQueueProvider(activity);
        }
        return mInstance;
    }

    private RequestQueue getRequestQueue() {

        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(activity.getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }

    public ImageLoader getImageLoader() {
        return mImageLoader;
    }

    public void makeRequest(int method, String url, JSONObject requestParams, final Response.Listener<JSONObject> listener){

        JsonObjectRequest request = new JsonObjectRequest(method, url, requestParams, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                listener.onResponse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error",error.getMessage());
            }
        });

        request.setRetryPolicy(new DefaultRetryPolicy(0, -1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        addToRequestQueue(request);

    }

    public void makeStringRequest(int method, String url, final Response.Listener<String> listener){


        StringRequest request = new StringRequest(method, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                listener.onResponse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error",error.getMessage());
            }
        });

        request.setRetryPolicy(new DefaultRetryPolicy(0, -1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        addToRequestQueue(request);

    }
}
