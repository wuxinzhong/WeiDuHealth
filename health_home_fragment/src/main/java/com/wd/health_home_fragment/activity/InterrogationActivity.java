package com.wd.health_home_fragment.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import com.wd.common.base.BaseActivity;
import com.wd.common.bean.DivisionBean;
import com.wd.common.bean.DoctorBean;
import com.wd.common.constraint.Constraint;
import com.wd.health_home_fragment.R;
import com.wd.health_home_fragment.adapter.DoctorRecycleAdapter;
import com.wd.health_home_fragment.adapter.InterrogationAdapter;
import com.wd.health_home_fragment.presenter.DivisionPresenter;

import java.util.List;

public class InterrogationActivity extends BaseActivity<DivisionPresenter> implements View.OnClickListener, Constraint.IDivisionView {

    private SimpleDraweeView interrogation_login;
    private CheckBox interrogation_news;
    private RecyclerView interrogation_recycle_kemu;
    private LinearLayout interrogation_zonghe;
    private LinearLayout interrogation_haoping;
    private LinearLayout interrogation_zixunSum;
    private LinearLayout interrogation_price_paixu;
    private ImageView interrogation_price_paixu_img;
    private SimpleDraweeView interrogation_img;
    private TextView interrogation_name;
    private TextView interrogation_zhiwei;
    private TextView interrogation_adress;
    private TextView interrogation_haopinglu;
    private TextView interrogation_sum;
    private ImageView interrogation_more;
    private TextView interrogation_price;
    private Button interrogation_btn_zixun;
    private ImageView interrogation_img_left;
    private RecyclerView interrogation_doctor_recycle;
    private ImageView interrogation_img_right;
    private InterrogationAdapter mInterrogationAdapter;
    private int mId;
    private int id;

    private static final String TAG = "InterrogationActivity";
    private DoctorRecycleAdapter mDoctorRecycleAdapter;

    @Override
    protected void initData() {

        //下面的医生列表
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(this);
        linearLayoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);
        interrogation_doctor_recycle.setLayoutManager(linearLayoutManager1);
        mDoctorRecycleAdapter = new DoctorRecycleAdapter();
        interrogation_doctor_recycle.setAdapter(mDoctorRecycleAdapter);
        mDoctorRecycleAdapter.setOnItemClickListener(new DoctorRecycleAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {

            }
        });



        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        interrogation_recycle_kemu.setLayoutManager(linearLayoutManager);
        mInterrogationAdapter = new InterrogationAdapter();
        interrogation_recycle_kemu.setAdapter(mInterrogationAdapter);
        mInterrogationAdapter.setPosition(mId);
        mInterrogationAdapter.setOnItemClickListener(new InterrogationAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                id = position;
                presenter.doctor(id, 1, 0, true, 6);
            }
        });

        presenter.division();
        presenter.doctor(id, 1, 0, true, 6);

    }

    @Override
    protected DivisionPresenter getpresenter() {
        return new DivisionPresenter();
    }

    protected void initView() {
        interrogation_login = (SimpleDraweeView) findViewById(R.id.interrogation_login);
        interrogation_news = (CheckBox) findViewById(R.id.interrogation_news);
        interrogation_recycle_kemu = (RecyclerView) findViewById(R.id.interrogation_recycle_kemu);
        interrogation_zonghe = (LinearLayout) findViewById(R.id.interrogation_zonghe);
        interrogation_haoping = (LinearLayout) findViewById(R.id.interrogation_haoping);
        interrogation_zixunSum = (LinearLayout) findViewById(R.id.interrogation_zixunSum);
        interrogation_price_paixu = (LinearLayout) findViewById(R.id.interrogation_price_paixu);
        interrogation_price_paixu_img = (ImageView) findViewById(R.id.interrogation_price_paixu_img);
        interrogation_img = (SimpleDraweeView) findViewById(R.id.interrogation_img);
        interrogation_name = (TextView) findViewById(R.id.interrogation_name);
        interrogation_zhiwei = (TextView) findViewById(R.id.interrogation_zhiwei);
        interrogation_adress = (TextView) findViewById(R.id.interrogation_adress);
        interrogation_haopinglu = (TextView) findViewById(R.id.interrogation_haopinglu);
        interrogation_sum = (TextView) findViewById(R.id.interrogation_sum);
        interrogation_more = (ImageView) findViewById(R.id.interrogation_more);
        interrogation_price = (TextView) findViewById(R.id.interrogation_price);
        interrogation_btn_zixun = (Button) findViewById(R.id.interrogation_btn_zixun);
        interrogation_img_left = (ImageView) findViewById(R.id.interrogation_img_left);
        interrogation_doctor_recycle = (RecyclerView) findViewById(R.id.interrogation_doctor_recycle);
        interrogation_img_right = (ImageView) findViewById(R.id.interrogation_img_right);

        interrogation_btn_zixun.setOnClickListener(this);
        interrogation_login.setOnClickListener(this);
        interrogation_news.setOnClickListener(this);
        interrogation_zonghe.setOnClickListener(this);
        interrogation_haoping.setOnClickListener(this);
        interrogation_zixunSum.setOnClickListener(this);
        interrogation_price_paixu.setOnClickListener(this);
    }

    @Override
    protected void initListener() {
        Intent intent = getIntent();
        mId = intent.getIntExtra("id", 0);
        Log.i(TAG, "initListener: " + mId);
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_interrogation;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.interrogation_btn_zixun) {

            Toast.makeText(this, "立即咨询", Toast.LENGTH_SHORT).show();

        } else if (v.getId() == R.id.interrogation_login) {

            Toast.makeText(this, "我的", Toast.LENGTH_SHORT).show();

        } else if (v.getId() == R.id.interrogation_news) {

            Toast.makeText(this, "消息", Toast.LENGTH_SHORT).show();

        } else if (v.getId() == R.id.interrogation_zonghe) {

            presenter.doctor(id, 1, 0, true, 6);

        } else if (v.getId() == R.id.interrogation_haoping) {

            presenter.doctor(id, 2, 0, true, 6);

        } else if (v.getId() == R.id.interrogation_zixunSum) {

            presenter.doctor(id, 3, 0, true, 6);

        } else if (v.getId() == R.id.interrogation_price_paixu) {

            presenter.doctor(id, 4, 1, true, 6);

        }

    }

    @Override
    public void IDivisionSuccess(DivisionBean divisionBean) {
        if (divisionBean.status.equals("0000")) {

            mInterrogationAdapter.addAll(divisionBean.result);
            mInterrogationAdapter.notifyDataSetChanged();

        }
    }

    @Override
    public void IDivisionError(String s) {

    }

    @Override
    public void IDoctorSuccess(DoctorBean doctorBean) {
        if (doctorBean.status.equals("0000")) {

            List<DoctorBean.ResultBean> result = doctorBean.result;

            Uri uri = Uri.parse(result.get(0).imagePic);
            interrogation_img.setImageURI(uri);

            interrogation_name.setText(result.get(0).doctorName);
            interrogation_zhiwei.setText(result.get(0).jobTitle);
            interrogation_adress.setText(result.get(0).inauguralHospital);
            interrogation_haopinglu.setText("好评率 " + result.get(0).praise);
            interrogation_sum.setText("服务和患者数 " + result.get(0).serverNum);


            mDoctorRecycleAdapter.clean();
            mDoctorRecycleAdapter.addAll(doctorBean.result);
            mDoctorRecycleAdapter.notifyDataSetChanged();



        }
    }

    @Override
    public void IDoctorError(String s) {

    }
}
