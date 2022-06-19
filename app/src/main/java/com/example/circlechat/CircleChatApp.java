package com.example.circlechat;

import android.app.Application;
import android.content.Context;

// Used to store the application context for later uses
public class CircleChatApp extends Application {
    private static Context context;

    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }
}
