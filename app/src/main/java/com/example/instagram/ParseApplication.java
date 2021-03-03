package com.example.instagram;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // Register your parse models
        ParseObject.registerSubclass(Post.class);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("fVoAXFOgM5lWg5rjbEfJDy0Mj2aNjNs7QUJaAHFu")
                .clientKey("AczrP2MEbKWEjaOMrZLeJu7DxaC7gCVRzXeDUblc")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}