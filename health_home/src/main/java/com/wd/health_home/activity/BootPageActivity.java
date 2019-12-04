package com.wd.health_home.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.wd.health_home.R;
import com.wd.health_home.adapter.BootViewPageAdapter;

import java.util.ArrayList;
import java.util.List;

public class BootPageActivity extends AppCompatActivity {

    private ViewPager boot_viewPage;
    private TextView boot_text;
    private RadioGroup boot_radio_group;
    private List<Integer> mList = new ArrayList<>();
    private Button boot_btn;
    private SharedPreferences mConfig;

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

        setContentView(R.layout.activity_boot_page);
        initView();

        mList.add(R.drawable.guide_pages_one);
        mList.add(R.drawable.guide_pages_two);
        mList.add(R.drawable.guide_pages_three);
        mList.add(R.drawable.guide_pages_four);
        mList.add(R.drawable.guide_pages_five);

        BootViewPageAdapter bootViewPageAdapter = new BootViewPageAdapter(mList);
        boot_viewPage.setAdapter(bootViewPageAdapter);
        boot_viewPage.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                boot_radio_group.check(boot_radio_group.getChildAt(i).getId());
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        boot_radio_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.boot_rb1) {

                    boot_btn.setVisibility(View.GONE);
                    boot_viewPage.setCurrentItem(0);
                    boot_text.setText("专业在线问诊");

                } else if (checkedId == R.id.boot_rb2) {

                    boot_btn.setVisibility(View.GONE);
                    boot_viewPage.setCurrentItem(1);
                    boot_text.setText("丰富的健康常识");

                } else if (checkedId == R.id.boot_rb3) {

                    boot_btn.setVisibility(View.GONE);
                    boot_viewPage.setCurrentItem(2);
                    boot_text.setText("专业在线问诊");

                } else if (checkedId == R.id.boot_rb4) {

                    boot_btn.setVisibility(View.GONE);
                    boot_viewPage.setCurrentItem(3);
                    boot_text.setText("丰富的健康常识");

                } else if (checkedId == R.id.boot_rb5) {

                    boot_btn.setVisibility(View.VISIBLE);
                    boot_viewPage.setCurrentItem(4);
                    boot_text.setText("专业在线问诊");

                }
            }
        });

        mConfig = getSharedPreferences("config", MODE_PRIVATE);
        boolean isFirst = mConfig.getBoolean("isFirstUse", false);
        if (isFirst) {
            Intent home = new Intent(BootPageActivity.this, HomeActivity.class);
            startActivity(home);
            finish();
        }

    }

    private void initView() {
        boot_viewPage = (ViewPager) findViewById(R.id.boot_viewPage);
        boot_text = (TextView) findViewById(R.id.boot_text);
        boot_radio_group = (RadioGroup) findViewById(R.id.boot_radio_group);
        boot_btn = (Button) findViewById(R.id.boot_btn);
        boot_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mConfig = getSharedPreferences("config", MODE_PRIVATE);
                SharedPreferences.Editor edit = mConfig.edit();
                edit.putBoolean("isFirstUse", true);
                edit.commit();

                startActivity(new Intent(BootPageActivity.this, HomeActivity.class));
                finish();
            }
        });
    }

}
