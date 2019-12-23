package com.wd.health_home_fragment.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;

import com.wd.common.base.BaseActivity;
import com.wd.common.bean.CommonDrugBean;
import com.wd.common.bean.DiseaseBean;
import com.wd.common.bean.DivisionBean;
import com.wd.common.bean.DrugBean;
import com.wd.common.bean.User;
import com.wd.common.constraint.Constraint;
import com.wd.common.dao.DaoMaster;
import com.wd.common.dao.DaoSession;
import com.wd.common.dao.UserDao;
import com.wd.health_home_fragment.R;
import com.wd.health_home_fragment.adapter.LeftAdapter;
import com.wd.health_home_fragment.adapter.LeftDrugAdapter;
import com.wd.health_home_fragment.adapter.RightAdapter;
import com.wd.health_home_fragment.adapter.RightDrugAdapter;
import com.wd.health_home_fragment.presenter.HomeTwoPresenter;
import com.wd.health_my.MyActivity;

import java.util.List;

public class DiseaseActivity extends BaseActivity<HomeTwoPresenter> implements Constraint.IHomeTow, View.OnClickListener {

    private SimpleDraweeView disease_login;
    private CheckBox disease_news;
    private TextView disease_text;
    private TextView drug_text;
    private RecyclerView disease_left_recycle;
    private RecyclerView disease_right_recycle;
    private LeftAdapter mLeftAdapter;
    private LeftDrugAdapter mLeftDrugAdapter;
    private RightAdapter mRightAdapter;
    private RightDrugAdapter mRightDrugAdapter;

    @Override
    protected void initData() {


        LinearLayoutManager left = new LinearLayoutManager(this);
        left.setOrientation(LinearLayoutManager.VERTICAL);
        disease_left_recycle.setLayoutManager(left);


        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);
        if (id == 1) {

            drug_text.setTextColor(Color.parseColor("#999999"));
            disease_text.setTextColor(Color.parseColor("#3087ea"));

            mLeftAdapter = new LeftAdapter();
            disease_left_recycle.setAdapter(mLeftAdapter);
            mLeftAdapter.setOnItemClickListener(new LeftAdapter.OnItemClickListener() {
                @Override
                public void onClick(int position) {

                    presenter.Disease(position);

                }
            });

            GridLayoutManager right = new GridLayoutManager(this, 2);
            disease_right_recycle.setLayoutManager(right);

            mRightAdapter = new RightAdapter();
            disease_right_recycle.setAdapter(mRightAdapter);
            mRightAdapter.setOnItemClickListener(new RightAdapter.OnItemClickListener() {
                @Override
                public void onClick(int position, String name) {
                    Intent intent1 = new Intent(DiseaseActivity.this, DiseaseDetailsActivity.class);
                    intent1.putExtra("id", position);
                    intent1.putExtra("name", name);
                    startActivity(intent1);
                }
            });

            presenter.Division();
            mLeftAdapter.notifyDataSetChanged();
            mRightAdapter.notifyDataSetChanged();

        } else if (id == 2) {

            disease_text.setTextColor(Color.parseColor("#999999"));
            drug_text.setTextColor(Color.parseColor("#3087ea"));

            mLeftDrugAdapter = new LeftDrugAdapter();
            disease_left_recycle.setAdapter(mLeftDrugAdapter);
            mLeftDrugAdapter.setOnItemClickListener(new LeftDrugAdapter.OnItemClickListener() {
                @Override
                public void onClick(int position) {
                    presenter.CommonDrug(position, true);
                }
            });

            GridLayoutManager right = new GridLayoutManager(this, 3);
            disease_right_recycle.setLayoutManager(right);

            mRightDrugAdapter = new RightDrugAdapter();
            disease_right_recycle.setAdapter(mRightDrugAdapter);
            mRightDrugAdapter.setOnItemClickListener(new RightDrugAdapter.OnItemClickListener() {
                @Override
                public void onClick(int position, String name) {
                    Intent intent1 = new Intent(DiseaseActivity.this, DrugDetailsActivity.class);
                    intent1.putExtra("id", position);
                    intent1.putExtra("name", name);
                    startActivity(intent1);
                }
            });

            presenter.Drug();

            mLeftDrugAdapter.notifyDataSetChanged();
            mRightDrugAdapter.notifyDataSetChanged();
        }

    }

    @Override
    protected HomeTwoPresenter getpresenter() {
        return new HomeTwoPresenter();
    }

    @Override
    protected void initView() {

        disease_login = (SimpleDraweeView) findViewById(R.id.disease_login);
        disease_login.setOnClickListener(this);
        disease_news = (CheckBox) findViewById(R.id.disease_news);
        disease_news.setOnClickListener(this);
        disease_text = (TextView) findViewById(R.id.disease_text);
        disease_text.setOnClickListener(this);
        drug_text = (TextView) findViewById(R.id.drug_text);
        drug_text.setOnClickListener(this);
        disease_left_recycle = (RecyclerView) findViewById(R.id.disease_left_recycle);
        disease_right_recycle = (RecyclerView) findViewById(R.id.disease_right_recycle);
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
            disease_login.setImageURI(uri);

        } else {
            Uri uri = Uri.parse("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=2060761043,284284863&fm=27&g=0.jpg");
            disease_login.setImageURI(uri);
        }


    }

    @Override
    protected int initLayout() {
        return R.layout.activity_disease;
    }

    @Override
    public void IDivisionSuccess(DivisionBean divisionBean) {
        if (divisionBean.status.equals("0000")) {

            mLeftAdapter.clear();

            mLeftAdapter.addAll(divisionBean.result);
            mLeftAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void IDivisionError(String s) {

    }

    @Override
    public void IDiseaseSuccess(DiseaseBean diseaseBean) {
        if (diseaseBean.status.equals("0000")) {

            mRightAdapter.clear();

            mRightAdapter.addAll(diseaseBean.result);
            mRightAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void IDiseaseError(String s) {

    }

    @Override
    public void IDrugSuccess(DrugBean drugBean) {
        if (drugBean.status.equals("0000")) {

            mLeftDrugAdapter.clear();

            mLeftDrugAdapter.addAll(drugBean.result);
            mLeftDrugAdapter.notifyDataSetChanged();

        }
    }

    @Override
    public void IDrugError(String s) {

    }

    @Override
    public void ICommonDrugSuccess(CommonDrugBean commonDrugBean) {

        if (commonDrugBean.status.equals("0000")) {

            mRightDrugAdapter.clear();

            mRightDrugAdapter.addAll(commonDrugBean.result);
            mRightDrugAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void ICommonDrugError(String s) {

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.disease_login) {

            startActivity(new Intent(this, MyActivity.class));

        } else if (v.getId() == R.id.disease_news) {

            Toast.makeText(this, "消息", Toast.LENGTH_SHORT).show();

        } else if (v.getId() == R.id.disease_text) {
            disease_text.setTextColor(Color.parseColor("#3087ea"));
            drug_text.setTextColor(Color.parseColor("#999999"));

            LinearLayoutManager left = new LinearLayoutManager(this);
            left.setOrientation(LinearLayoutManager.VERTICAL);
            disease_left_recycle.setLayoutManager(left);

            mLeftAdapter = new LeftAdapter();
            disease_left_recycle.setAdapter(mLeftAdapter);
            mLeftAdapter.setOnItemClickListener(new LeftAdapter.OnItemClickListener() {
                @Override
                public void onClick(int position) {

                    presenter.Disease(position);

                }
            });

            GridLayoutManager right = new GridLayoutManager(this, 2);
            disease_right_recycle.setLayoutManager(right);

            mRightAdapter = new RightAdapter();
            disease_right_recycle.setAdapter(mRightAdapter);
            mRightAdapter.setOnItemClickListener(new RightAdapter.OnItemClickListener() {
                @Override
                public void onClick(int position, String name) {
                    Intent intent1 = new Intent(DiseaseActivity.this, DiseaseDetailsActivity.class);
                    intent1.putExtra("id", position);
                    intent1.putExtra("name", name);
                    startActivity(intent1);
                }
            });

            presenter.Division();
            mLeftAdapter.notifyDataSetChanged();
            mRightAdapter.notifyDataSetChanged();

        } else if (v.getId() == R.id.drug_text) {

            disease_text.setTextColor(Color.parseColor("#999999"));
            drug_text.setTextColor(Color.parseColor("#3087ea"));

            LinearLayoutManager left = new LinearLayoutManager(this);
            left.setOrientation(LinearLayoutManager.VERTICAL);
            disease_left_recycle.setLayoutManager(left);

            mLeftDrugAdapter = new LeftDrugAdapter();
            disease_left_recycle.setAdapter(mLeftDrugAdapter);
            mLeftDrugAdapter.setOnItemClickListener(new LeftDrugAdapter.OnItemClickListener() {
                @Override
                public void onClick(int position) {
                    presenter.CommonDrug(position, true);
                }
            });

            GridLayoutManager right = new GridLayoutManager(this, 3);
            disease_right_recycle.setLayoutManager(right);

            mRightDrugAdapter = new RightDrugAdapter();
            disease_right_recycle.setAdapter(mRightDrugAdapter);
            mRightDrugAdapter.setOnItemClickListener(new RightDrugAdapter.OnItemClickListener() {
                @Override
                public void onClick(int position, String name) {
                    Intent intent1 = new Intent(DiseaseActivity.this, DrugDetailsActivity.class);
                    intent1.putExtra("id", position);
                    intent1.putExtra("name", name);
                    startActivity(intent1);
                }
            });

            presenter.Drug();

            mLeftDrugAdapter.notifyDataSetChanged();
            mRightDrugAdapter.notifyDataSetChanged();

        }
    }
}
