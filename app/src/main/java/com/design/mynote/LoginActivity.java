package com.design.mynote;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by 杰‘z on 2016/8/9.
 */
public class LoginActivity extends Activity implements View.OnClickListener {

    private TextView mTV_Register;
    private TextView mTV_Forget;
    private Button mBtn_Login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        initView();

    }

    private void initView() {

        mBtn_Login = (Button) findViewById(R.id.bt_go);
        mTV_Forget = (TextView) findViewById(R.id.tv_forget);
        mTV_Register = (TextView) findViewById(R.id.tv_register);
        mBtn_Login.setOnClickListener(this);
        mTV_Forget.setOnClickListener(this);
        mTV_Register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_go:
                Intent intent0 = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent0);
                finish();
                break;

            case R.id.tv_register:
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_forget:
                Intent intent1 = new Intent(LoginActivity.this, FindPassActivity.class);
                startActivity(intent1);
                break;
        }
    }
}
