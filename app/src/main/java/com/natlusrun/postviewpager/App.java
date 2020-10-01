package com.natlusrun.postviewpager;

import android.app.Application;

import com.natlusrun.postviewpager.data.network.MockerService;

public class App extends Application {
    public static MockerService mockerService;

    @Override
    public void onCreate() {
        super.onCreate();
        mockerService = new MockerService();
    }
}
