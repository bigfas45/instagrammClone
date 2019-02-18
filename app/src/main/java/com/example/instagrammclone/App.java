package com.example.instagrammclone;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("zhjTPWAkaNDq1huO65Vlhwx5qFHz82XNkdOg99sW")
                // if defined
                .clientKey("TdXp8KMxLdfv5XpM03GJNSyfI3Rby9LaaIWmWOBR")
                .server("https://parseapi.back4app.com/")
                .build()
        );
    }
}
