package com.design.mynote;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by 杰‘z on 2016/8/13.
 */
public class ActivityUtils {

    public static void next(Activity curActivity, Class<?> nextActivity,
                            Bundle bundle) {
        Intent intent = new Intent(curActivity, nextActivity);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        curActivity.startActivity(intent);
        // curActivity.overridePendingTransition(R.anim.push_left_in,
        // R.anim.push_left_out);
    }

    public static void next(Activity curActivity, Class<?> nextActivity,
                            Intent intent) {
        intent.setClass(curActivity, nextActivity);
        curActivity.startActivity(intent);
        // curActivity.overridePendingTransition(R.anim.push_left_in,
        // R.anim.push_left_out);
    }

    public static void next(Activity curActivity, Class<?> nextActivity,
                            Intent intent, int reqcode) {
        intent.setClass(curActivity, nextActivity);
        if (reqcode != -1) {
            curActivity.startActivityForResult(intent, reqcode);
            // curActivity.overridePendingTransition(R.anim.push_left_in,
            // R.anim.push_left_out);
        }

    }

    public static void next(Activity curActivity, Class<?> nextActivity,
                            int reqcode) {
        Intent intent = new Intent(curActivity, nextActivity);
        curActivity.startActivityForResult(intent, reqcode);
    }

    public static void next(Activity curActivity, Class<?> nextActivity) {
        Intent intent = new Intent(curActivity, nextActivity);
        curActivity.startActivity(intent);
        // curActivity.overridePendingTransition(R.anim.push_left_in,
        // R.anim.push_left_out);
    }

    public static void back(Activity curActivity, Class<?> nextActivity) {
        Intent intent = new Intent(curActivity, nextActivity);
        curActivity.startActivity(intent);
        // curActivity.overridePendingTransition(R.anim.push_left_out,
        // R.anim.push_left_in);
    }

    public static void next(Activity curActivity, Class<?> nextActivity,
                            int in, int out) {
        Intent intent = new Intent(curActivity, nextActivity);
        curActivity.startActivity(intent);
        curActivity.overridePendingTransition(in, out);
    }

    public static void next(Activity curActivity, Class<?> nextActivity,
                            Intent intent, int in, int out) {
        intent.setClass(curActivity, nextActivity);
        curActivity.startActivity(intent);
        curActivity.overridePendingTransition(in, out);
    }

    public static void back(Activity curActivity, Class<?> nextActivity,
                            int in, int out) {
        Intent intent = new Intent(curActivity, nextActivity);
        curActivity.startActivity(intent);
        curActivity.overridePendingTransition(in, out);
    }
}
