package com.wd.health_home_fragment.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.common.base.BaseActivity;
import com.wd.common.bean.DiseaseDetailsBean;
import com.wd.common.constraint.Constraint;
import com.wd.health_home_fragment.R;
import com.wd.health_home_fragment.presenter.DiseaseDetailsPresenter;

public class DiseaseDetailsActivity extends BaseActivity<DiseaseDetailsPresenter> implements Constraint.IDiseaseThree, View.OnClickListener {

    private SimpleDraweeView disease_details_login;
    private CheckBox disease_details_news;
    private TextView disease_details_name;
    private TextView disease_details_pathology;
    private TextView disease_details_symptom;
    private TextView disease_details_appropriate;
    private TextView disease_details_western;
    private TextView disease_details_chinese;

    @Override
    protected void initData() {


        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);
        String name = intent.getStringExtra("name");

        disease_details_name.setText(name);
        presenter.disease(id);

    }

    @Override
    protected DiseaseDetailsPresenter getpresenter() {
        return new DiseaseDetailsPresenter();
    }

    @Override
    protected void initView() {

        disease_details_login = (SimpleDraweeView) findViewById(R.id.disease_details_login);
        disease_details_login.setOnClickListener(this);
        disease_details_news = (CheckBox) findViewById(R.id.disease_details_news);
        disease_details_news.setOnClickListener(this);
        disease_details_name = (TextView) findViewById(R.id.disease_details_name);
        disease_details_pathology = (TextView) findViewById(R.id.disease_details_pathology);
        disease_details_symptom = (TextView) findViewById(R.id.disease_details_symptom);
        disease_details_appropriate = (TextView) findViewById(R.id.disease_details_appropriate);
        disease_details_western = (TextView) findViewById(R.id.disease_details_western);
        disease_details_chinese = (TextView) findViewById(R.id.disease_details_chinese);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected int initLayout() {
        return R.layout.activity_disease_details;
    }

    @Override
    public void IDiseaseSuccess(DiseaseDetailsBean diseaseDetailsBean) {
        if (diseaseDetailsBean.status.equals("0000")) {

            DiseaseDetailsBean.ResultBean result = diseaseDetailsBean.result;

            if (result.pathology != null)
                disease_details_pathology.setText(result.pathology);

            if (result.symptom != null)
                disease_details_symptom.setText(result.symptom);

            if (result.benefitTaboo != null)
                disease_details_appropriate.setText(result.benefitTaboo);

            if (result.westernMedicineTreatment != null)
                disease_details_western.setText(result.westernMedicineTreatment);

            if (result.chineseMedicineTreatment != null)
                disease_details_chinese.setText(result.chineseMedicineTreatment);

        }
    }

    @Override
    public void IDiseaseError(String s) {

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.disease_details_login) {

            Toast.makeText(this, "登录", Toast.LENGTH_SHORT).show();

        } else if (v.getId() == R.id.disease_details_news) {

            Toast.makeText(this, "消息", Toast.LENGTH_SHORT).show();

        }
    }
}
