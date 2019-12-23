package com.wd.health_home_fragment.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;

import com.wd.common.base.BaseActivity;
import com.wd.common.bean.DiseaseDetailsBean;
import com.wd.common.bean.User;
import com.wd.common.constraint.Constraint;
import com.wd.common.dao.DaoMaster;
import com.wd.common.dao.DaoSession;
import com.wd.common.dao.UserDao;
import com.wd.health_home_fragment.R;
import com.wd.health_home_fragment.presenter.DiseaseDetailsPresenter;
import com.wd.health_my.MyActivity;

import java.util.List;

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

        SharedPreferences config = getSharedPreferences("config", MODE_PRIVATE);
        boolean isFirst = config.getBoolean("isFirst", false);
        if (isFirst) {

            DaoSession daoSession = DaoMaster.newDevSession(this, UserDao.TABLENAME);
            UserDao userDao = daoSession.getUserDao();
            List<User> users = userDao.loadAll();

            String headPic = users.get(0).getHeadPic();

            Uri uri = Uri.parse(headPic);
            disease_details_login.setImageURI(uri);

        } else {
            Uri uri = Uri.parse("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=2060761043,284284863&fm=27&g=0.jpg");
            disease_details_login.setImageURI(uri);
        }

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

            startActivity(new Intent(this, MyActivity.class));

        } else if (v.getId() == R.id.disease_details_news) {

            Toast.makeText(this, "消息", Toast.LENGTH_SHORT).show();

        }
    }
}
