package com.wd.health_login_and_regress.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.wd.health_login_and_regress.R;

public class NewsPwdActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView my_new_pwd_back;
    private EditText my_new_pwd_pwd;
    private CheckBox my_new_pwd_hide_eye;
    private EditText my_new_pwd_pwd1;
    private CheckBox my_new_pwd_hide_eye1;
    private Button my_new_pwd_btn_finish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_pwd);
        initView();
    }

    private void initView() {
        my_new_pwd_back = (ImageView) findViewById(R.id.my_new_pwd_back);
        my_new_pwd_pwd = (EditText) findViewById(R.id.my_new_pwd_pwd);
        my_new_pwd_hide_eye = (CheckBox) findViewById(R.id.my_new_pwd_hide_eye);
        my_new_pwd_pwd1 = (EditText) findViewById(R.id.my_new_pwd_pwd1);
        my_new_pwd_hide_eye1 = (CheckBox) findViewById(R.id.my_new_pwd_hide_eye1);
        my_new_pwd_btn_finish = (Button) findViewById(R.id.my_new_pwd_btn_finish);

        my_new_pwd_btn_finish.setOnClickListener(this);
        my_new_pwd_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.my_new_pwd_btn_finish) {

            finish();

        } else if (v.getId() == R.id.my_new_pwd_back) {
            finish();
        }

    }

}
