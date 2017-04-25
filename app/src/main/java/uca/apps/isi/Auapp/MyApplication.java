package uca.apps.isi.Auapp;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by isi3 on 18/4/2017.
 */

public class MyApplication extends Application {
    public static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        // Initialize Realm
        Realm.init(this);



        instance = this;


    }



}
