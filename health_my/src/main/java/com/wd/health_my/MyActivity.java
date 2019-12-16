package com.wd.health_my;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.common.bean.User;
import com.wd.common.dao.DaoMaster;
import com.wd.common.dao.DaoSession;
import com.wd.common.dao.UserDao;
import com.wd.health_login_and_regress.activity.LoginActivity;
import com.wd.health_login_and_regress.activity.RegressActivity;
import com.wd.health_login_and_regress.toast.CustomToast;

import java.util.List;

public class MyActivity extends AppCompatActivity implements View.OnClickListener {

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

        setContentView(R.layout.activity_my);
        initView();


        SharedPreferences config = getSharedPreferences("config", MODE_PRIVATE);
        boolean isFirst = config.getBoolean("isFirst", false);
        if (isFirst) {

            DaoSession daoSession = DaoMaster.newDevSession(this, UserDao.TABLENAME);
            UserDao userDao = daoSession.getUserDao();
            List<User> users = userDao.loadAll();

            String headPic = users.get(0).getHeadPic();
            String nickName = users.get(0).getNickName();

            Uri uri = Uri.parse(headPic);
            my_header.setImageURI(uri);
            my_userName.setText(nickName);

        } else {
            Toast.makeText(this, "请先登录", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences config = getSharedPreferences("config", MODE_PRIVATE);
        boolean isFirst = config.getBoolean("isFirst", false);
        if (isFirst) {

            DaoSession daoSession = DaoMaster.newDevSession(this, UserDao.TABLENAME);
            UserDao userDao = daoSession.getUserDao();
            List<User> users = userDao.loadAll();

            String headPic = users.get(0).getHeadPic();
            String nickName = users.get(0).getNickName();

            Uri uri = Uri.parse(headPic);
            my_header.setImageURI(uri);
            my_userName.setText(nickName);

        } else {
            Toast.makeText(this, "请先登录", Toast.LENGTH_SHORT).show();
        }
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
        my_header.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.my_back) {
            finish();
        } else if (v.getId() == R.id.my_sign) {

        } else if (v.getId() == R.id.my_header) {

            SharedPreferences config = getSharedPreferences("config", MODE_PRIVATE);
            boolean isFirst = config.getBoolean("isFirst", false);
            if (isFirst) {
                Toast.makeText(this, "已经登录", Toast.LENGTH_SHORT).show();
            } else {
                startActivity(new Intent(MyActivity.this, LoginActivity.class));
            }


        }
    }
}
