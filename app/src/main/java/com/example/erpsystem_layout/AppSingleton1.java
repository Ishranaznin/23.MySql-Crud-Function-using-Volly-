package com.example.erpsystem_layout;



import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

public class AppSingleton1 {
    private static com.example.erpsystem_layout.AppSingleton1 mAppSingleton1Instance;
    private RequestQueue mRequestQueue;
    private static Context mContext;

    private AppSingleton1(Context context) {
        mContext = context;
        mRequestQueue = getRequestQueue();
    }

    public static synchronized com.example.erpsystem_layout.AppSingleton1 getInstance(Context context) {
        if (mAppSingleton1Instance == null) {
            mAppSingleton1Instance = new com.example.erpsystem_layout.AppSingleton1(context);
        }
        return mAppSingleton1Instance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            mRequestQueue = Volley.newRequestQueue(mContext.getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        req.setTag(tag);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }
}