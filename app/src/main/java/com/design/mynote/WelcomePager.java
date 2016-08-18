package com.design.mynote;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by 杰‘z on 2016/8/10.
 */
public class WelcomePager extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);

        // shared = getSharedPreferences("guide", Activity.MODE_PRIVATE);
        // editor = shared.edit();
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                Intent intent = new Intent(WelcomePager.this, LoginActivity.class);
                startActivity(intent);
                finish();
                // boolean flag = shared.getBoolean("first", false);
                // if (flag) {
                // Intent intent = new Intent(Launcher.this,
                // MainActivity.class);
                // startActivity(intent);
                // finish();
                // } else {
                // editor.putBoolean("first", true);
                // editor.commit();
                // Intent intent = new Intent(Launcher.this, Guide.class);
                // startActivity(intent);
                // finish();
                // }
            }
        };
        timer.schedule(task, 3000);
    }
}
