package com.wd.health_my;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView my_back;
    private ImageView my_news;
    private SimpleDraweeView my_header;
    private TextView my_userName;
    private Button my_sign;
    private ImageView dang;
    private RelativeLayout my_present;
    private ImageView li;
    private RelativeLayout my_history;
    private LinearLayout wenzhen;
    private LinearLayout my_document;
    private LinearLayout my_opinion;
    private LinearLayout my_attention;
    private LinearLayout my_wallet;
    private LinearLayout my_movie;
    private LinearLayout my_task;
    private LinearLayout my_collect;
    private LinearLayout my_patients;
    private LinearLayout my_setting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setNavigationBarColor(Color.TRANSPARENT);
        }

        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        my_back = (ImageView) findViewById(R.id.my_back);
        my_news = (ImageView) findViewById(R.id.my_news);
        my_header = (SimpleDraweeView) findViewById(R.id.my_header);
        my_userName = (TextView) findViewById(R.id.my_userName);
        my_sign = (Button) findViewById(R.id.my_sign);
        dang = (ImageView) findViewById(R.id.dang);
        my_present = (RelativeLayout) findViewById(R.id.my_present);
        li = (ImageView) findViewById(R.id.li);
        my_history = (RelativeLayout) findViewById(R.id.my_history);
        wenzhen = (LinearLayout) findViewById(R.id.wenzhen);
        my_document = (LinearLayout) findViewById(R.id.my_document);
        my_opinion = (LinearLayout) findViewById(R.id.my_opinion);
        my_attention = (LinearLayout) findViewById(R.id.my_attention);
        my_wallet = (LinearLayout) findViewById(R.id.my_wallet);
        my_movie = (LinearLayout) findViewById(R.id.my_movie);
        my_task = (LinearLayout) findViewById(R.id.my_task);
        my_collect = (LinearLayout) findViewById(R.id.my_collect);
        my_patients = (LinearLayout) findViewById(R.id.my_patients);
        my_setting = (LinearLayout) findViewById(R.id.my_setting);

        my_sign.setOnClickListener(this);
        my_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.my_back) {
            finish();
        }
    }
}
