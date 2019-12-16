package com.wd.health_home_fragment.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.common.base.BaseActivity;
import com.wd.common.bean.DrugDetailsBean;
import com.wd.common.bean.User;
import com.wd.common.constraint.Constraint;
import com.wd.common.dao.DaoMaster;
import com.wd.common.dao.DaoSession;
import com.wd.common.dao.UserDao;
import com.wd.health_home_fragment.R;
import com.wd.health_home_fragment.presenter.DrugDetailsPresenter;
import com.wd.health_my.MyActivity;

import java.util.List;

public class DrugDetailsActivity extends BaseActivity<DrugDetailsPresenter> implements Constraint.IDrugThree, View.OnClickListener {

    private SimpleDraweeView drug_details_login;
    private CheckBox drug_details_news;
    private TextView drug_details_name;
    private TextView drug_details_component;
    private TextView drug_details_taboo;
    private TextView drug_details_effect;
    private TextView drug_details_usage;
    private TextView drug_details_shape;
    private TextView drug_details_packing;
    private TextView drug_details_sideEffects;
    private TextView drug_details_storage;
    private TextView drug_details_mindMatter;
    private TextView drug_details_approvalNumber;

    @Override
    protected void initData() {
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);
        String name = intent.getStringExtra("name");
        drug_details_name.setText(name);
        presenter.drugDetails(id);
    }

    @Override
    protected DrugDetailsPresenter getpresenter() {
        return new DrugDetailsPresenter();
    }

    @Override
    protected void initView() {

        drug_details_login = (SimpleDraweeView) findViewById(R.id.drug_details_login);
        drug_details_login.setOnClickListener(this);
        drug_details_news = (CheckBox) findViewById(R.id.drug_details_news);
        drug_details_news.setOnClickListener(this);
        drug_details_name = (TextView) findViewById(R.id.drug_details_name);
        drug_details_component = (TextView) findViewById(R.id.drug_details_component);
        drug_details_taboo = (TextView) findViewById(R.id.drug_details_taboo);
        drug_details_effect = (TextView) findViewById(R.id.drug_details_effect);
        drug_details_usage = (TextView) findViewById(R.id.drug_details_usage);
        drug_details_shape = (TextView) findViewById(R.id.drug_details_shape);
        drug_details_packing = (TextView) findViewById(R.id.drug_details_packing);
        drug_details_sideEffects = (TextView) findViewById(R.id.drug_details_sideEffects);
        drug_details_storage = (TextView) findViewById(R.id.drug_details_storage);
        drug_details_mindMatter = (TextView) findViewById(R.id.drug_details_mindMatter);
        drug_details_approvalNumber = (TextView) findViewById(R.id.drug_details_approvalNumber);
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
            drug_details_login.setImageURI(uri);

        } else {
            Uri uri = Uri.parse("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=2060761043,284284863&fm=27&g=0.jpg");
            drug_details_login.setImageURI(uri);
        }

    }

    @Override
    protected int initLayout() {
        return R.layout.activity_drug_details;
    }

    @Override
    public void IDrugSuccess(DrugDetailsBean drugDetailsBean) {
        if (drugDetailsBean.status.equals("0000")) {
            DrugDetailsBean.ResultBean result = drugDetailsBean.result;

            if (result.component != null)
                drug_details_component.setText(result.component);

            if (result.taboo != null)
                drug_details_taboo.setText(result.taboo);

            if (result.effect != null)
                drug_details_effect.setText(result.effect);

            if (result.usage != null)
                drug_details_usage.setText(result.usage);

            if (result.shape != null)
                drug_details_shape.setText(result.shape);

            if (result.packing != null)
                drug_details_packing.setText(result.packing);

            if (result.sideEffects != null)
                drug_details_sideEffects.setText(result.sideEffects);

            if (result.storage != null)
                drug_details_storage.setText(result.storage);

            if (result.mindMatter != null)
                drug_details_mindMatter.setText(result.mindMatter);

            if (result.approvalNumber != null)
                drug_details_approvalNumber.setText(result.approvalNumber);


        }
    }

    @Override
    public void IDrugError(String s) {

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.drug_details_login) {

            startActivity(new Intent(this, MyActivity.class));

        } else if (v.getId() == R.id.drug_details_news) {

            Toast.makeText(this, "消息", Toast.LENGTH_SHORT).show();

        }
    }
}
