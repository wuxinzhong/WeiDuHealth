package com.wd.health_login_and_regress.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.wd.health_login_and_regress.R;

public class ResetPwdActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView my_reset_back;
    private EditText my_reset_email;
    private Button my_reset_huo_email;
    private EditText my_reset_code;
    private Button my_reset_btn_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_pwd);
        initView();
    }

    private void initView() {
        my_reset_back = (ImageView) findViewById(R.id.my_reset_back);
        my_reset_email = (EditText) findViewById(R.id.my_reset_email);
        my_reset_huo_email = (Button) findViewById(R.id.my_reset_huo_email);
        my_reset_code = (EditText) findViewById(R.id.my_reset_code);
        my_reset_btn_next = (Button) findViewById(R.id.my_reset_btn_next);

        my_reset_huo_email.setOnClickListener(this);
        my_reset_btn_next.setOnClickListener(this);
        my_reset_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.my_reset_huo_email) {


        } else if (v.getId() == R.id.my_reset_btn_next) {

            startActivity(new Intent(ResetPwdActivity.this, NewsPwdActivity.class));
            finish();

        } else if (v.getId() == R.id.my_reset_back) {
            finish();
        }
    }
}
