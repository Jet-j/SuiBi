package com.design.mynote;

import android.app.Application;

/**
 * Created by 杰‘z on 2016/8/13.
 */
public class MyNoteApplication extends Application {

    private static User user;



    @Override
    public void onCreate() {
        super.onCreate();
        initUtils();
    }

    private void initUtils() {
        ToastUtils.setContext(this);
//        NetworkUtils.setContext(this);
//        VolleyRequestQueue.setContext(this);
        SharedPreferenceUtils.initSharedPreference(this);
        DensityUtils.setContext(this);
    }

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        MyNoteApplication.user = user;
    }
}
