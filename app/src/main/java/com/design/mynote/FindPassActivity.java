package com.design.mynote;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.TimerTask;

import me.wangyuwei.slackloadingview.SlackLoadingView;

/**
 * Created by 杰‘z on 2016/8/11.
 */
public class FindPassActivity extends Activity {

    private ImageView mImageView;
    private Handler mHandler;
    private SlackLoadingView mSlackLoadingView;
    private TextView mLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.findpass);

        initView();

        final TimerTask task = new TimerTask() {

            @Override
            public void run() {

//                mProgressDialog.dismiss();
                mSlackLoadingView.setVisibility(View.GONE);
                mLoading.setVisibility(View.GONE);
                Toast.makeText(FindPassActivity.this, "网络错误，请检查网络!", Toast.LENGTH_SHORT).show();
                mImageView.setVisibility(View.VISIBLE);


            }
        };

        mHandler.postDelayed(task, 3000);

    }

    private void initView() {
//        mProgressDialog = new ProgressDialog(FindPassActivity.this);
//        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//        mProgressDialog.setMessage("正在加载...");
//        mProgressDialog.setCancelable(false);
//        mProgressDialog.show();
        mImageView = (ImageView) findViewById(R.id.error);
        mImageView.setVisibility(View.GONE);
        mHandler = new Handler();
        mSlackLoadingView = (SlackLoadingView) findViewById(R.id.loading_view);
        mSlackLoadingView.setLineLength(0.05f);
        mSlackLoadingView.setDuration(0.2f);
        mSlackLoadingView.start();
        mLoading = (TextView) findViewById(R.id.loading);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
