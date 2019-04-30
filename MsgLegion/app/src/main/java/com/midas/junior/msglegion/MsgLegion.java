package com.midas.junior.msglegion;

import android.app.Application;

import com.google.firebase.FirebaseApp;

public class MsgLegion extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseApp.initializeApp(this);
    }
}
