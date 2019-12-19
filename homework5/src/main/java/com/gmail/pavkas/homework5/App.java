package com.gmail.pavkas.homework5;

import android.app.Application;

import androidx.room.Room;

public class App extends Application {

    private static App instance;
    private static PersonDatabase database;

    public void onCreate() {
        super.onCreate();
        instance = this;
        database = Room.databaseBuilder(this, PersonDatabase.class, "person_database").allowMainThreadQueries()
                .build();
    }

    public static App getInstance() {
        return instance;
    }

    public PersonDatabase getDatabase() {
        return database;
    }
}
