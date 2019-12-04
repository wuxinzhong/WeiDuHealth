package com.wd.health_home.activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.wd.health_home.R;
import com.wd.health_home.adapter.PageAdapter;
import com.wd.health_home.fragment.Frag_movie;
import com.wd.health_home.fragment.Frag_quan;
import com.wd.health_home.fragment.Frag_shou;

import java.util.ArrayList;
import java.util.List;

import android.view.WindowManager;
import android.widget.LinearLayout;

import com.wd.health_home.R;



public class HomeActivity extends AppCompatActivity {



    private LinearLayout bg;
    private List<Fragment> list=new  ArrayList<>();
    private ViewPager vp;
    private RadioGroup rg;
    private RadioButton shou,quan,movie;

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


    }





    private void initView() {
        bg = (LinearLayout) findViewById(R.id.bg);
        vp = findViewById(R.id.vp);
        rg = findViewById(R.id.rg);
        shou = findViewById(R.id.rb_shou);
        quan = findViewById(R.id.rb_quan);
        movie = findViewById(R.id.rb_movie);
        list.add(new Frag_shou());
        list.add(new Frag_quan());
        list.add(new Frag_movie());
        PageAdapter pageAdapter = new PageAdapter(getSupportFragmentManager(),list);
        vp.setAdapter(pageAdapter);
        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                switch (i){
                    case 0:shou.setChecked(true);break;
                    case 1:quan.setChecked(true);break;
                    case 2:movie.setChecked(true);break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rb_shou) {
                    vp.setCurrentItem(0);
                } else if (checkedId == R.id.rb_quan) {
                    vp.setCurrentItem(1);
                } else if (checkedId == R.id.rb_movie) {
                    vp.setCurrentItem(2);
                }
            }
        });
    }
}
