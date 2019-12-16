package com.wd.health_home.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.wd.health_home.R;
import com.wd.health_home_fragment.fragment.HomeFragment;
import com.wd.health_movie_fragment.MovieFragment;
import com.wd.health_sick_circle_fragment.SickCircleFragment;
import com.wd.health_write_sick_circle_activity.WriteSickCircleActivity;

import java.util.ArrayList;
import java.util.List;


public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView sick_circle_img;
    private ImageView comments_img;
    private HomeFragment mHomeFragment;
    private SickCircleFragment mSickCircleFragment;
    private MovieFragment mMovieFragment;
    private long mExitTime;
    private ImageView rb_shou_wei;
    private ImageView rb_shou_xuan;
    private ImageView rb_movie_wei;
    private ImageView rb_movie_xuan;
    private LinearLayout home_lin;
    private LinearLayout movie_lin;
    private LinearLayout sick_lin;

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


        setContentView(R.layout.activity_home);
        initView();


        mHomeFragment = new HomeFragment();
        mSickCircleFragment = new SickCircleFragment();
        mMovieFragment = new MovieFragment();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.vp, mHomeFragment)
                .add(R.id.vp, mSickCircleFragment)
                .add(R.id.vp, mMovieFragment)
                .show(mHomeFragment)
                .hide(mSickCircleFragment)
                .hide(mMovieFragment)
                .commit();
    }


    public void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
    }

    private void initView() {
        sick_circle_img = (ImageView) findViewById(R.id.sick_circle_img);
        comments_img = (ImageView) findViewById(R.id.comments_img);
        rb_shou_wei = (ImageView) findViewById(R.id.rb_shou_wei);
        rb_shou_xuan = (ImageView) findViewById(R.id.rb_shou_xuan);
        rb_movie_wei = (ImageView) findViewById(R.id.rb_movie_wei);
        rb_movie_xuan = (ImageView) findViewById(R.id.rb_movie_xuan);
        home_lin = (LinearLayout) findViewById(R.id.home_lin);
        home_lin.setOnClickListener(this);
        movie_lin = (LinearLayout) findViewById(R.id.movie_lin);
        movie_lin.setOnClickListener(this);
        sick_lin = (LinearLayout) findViewById(R.id.sick_lin);
        sick_lin.setOnClickListener(this);
        comments_img.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        comments_img.setVisibility(View.GONE);
        rb_movie_xuan.setVisibility(View.GONE);
        rb_shou_xuan.setVisibility(View.GONE);

        sick_circle_img.setVisibility(View.VISIBLE);
        rb_movie_wei.setVisibility(View.VISIBLE);
        rb_shou_wei.setVisibility(View.VISIBLE);

        if (v.getId() == R.id.home_lin) {
            rb_shou_xuan.setVisibility(View.VISIBLE);
            rb_shou_wei.setVisibility(View.GONE);

            getSupportFragmentManager()
                    .beginTransaction()
                    .show(mHomeFragment)
                    .hide(mMovieFragment)
                    .hide(mSickCircleFragment)
                    .commit();
        } else if (v.getId() == R.id.sick_lin) {
            comments_img.setVisibility(View.VISIBLE);
            sick_circle_img.setVisibility(View.GONE);

            getSupportFragmentManager()
                    .beginTransaction()
                    .show(mSickCircleFragment)
                    .hide(mMovieFragment)
                    .hide(mHomeFragment)
                    .commit();

        } else if (v.getId() == R.id.movie_lin) {
            rb_movie_xuan.setVisibility(View.VISIBLE);
            rb_movie_wei.setVisibility(View.GONE);

            getSupportFragmentManager()
                    .beginTransaction()
                    .show(mMovieFragment)
                    .hide(mHomeFragment)
                    .hide(mSickCircleFragment)
                    .commit();

        } else if (v.getId() == R.id.comments_img) {

            comments_img.setVisibility(View.VISIBLE);
            sick_circle_img.setVisibility(View.GONE);

            startActivity(new Intent(HomeActivity.this, WriteSickCircleActivity.class));
        }
    }

    //对返回键进行监听
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {

            exit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void exit() {
        if ((System.currentTimeMillis() - mExitTime) > 2000) {
            Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
            mExitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }
    }
}
