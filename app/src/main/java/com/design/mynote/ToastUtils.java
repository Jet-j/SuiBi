package com.design.mynote;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

/**
 * Created by 杰‘z on 2016/8/13.
 */
public class ToastUtils {


    private static Context context;
    private static Toast mToast;
    private static Handler mToastHandler = new Handler();
    private static Runnable r = new Runnable() {
        public void run() {
            mToast.cancel();
        }
    };
    public static void showToast(String msg){
        mToastHandler.removeCallbacks(r);
        if (mToast != null) {
            mToast.setText(msg);
        } else {
            mToast = Toast.makeText(context, msg, Toast.LENGTH_LONG);
        }
        mToastHandler.postDelayed(r, 5 * 1000);
        mToast.show();
    }

    public static void setContext(Context context) {
        ToastUtils.context = context;
    }
}
