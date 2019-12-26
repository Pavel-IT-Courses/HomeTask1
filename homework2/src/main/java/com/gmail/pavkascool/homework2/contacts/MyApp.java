package com.gmail.pavkascool.homework2.contacts;

import android.app.Application;

public class MyApp extends Application {
    private static PersonsHolder personHolder;
    private static MyApp instance;


    public static MyApp getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        personHolder = PersonsHolder.getInstance();
    }

    public static PersonsHolder getPersonsHolder() {
        return personHolder;
    }
}
