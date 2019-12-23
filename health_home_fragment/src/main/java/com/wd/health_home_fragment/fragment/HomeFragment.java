package com.wd.health_home_fragment.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.facebook.drawee.view.SimpleDraweeView;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.transformers.Transformer;

import com.wd.common.base.BaseFragment;
import com.wd.common.bean.BannerBean;
import com.wd.common.bean.DivisionBean;
import com.wd.common.bean.InformationBean;
import com.wd.common.bean.InformationListBean;
import com.wd.common.bean.User;
import com.wd.common.constraint.Constraint;
import com.wd.common.dao.DaoMaster;
import com.wd.common.dao.DaoSession;
import com.wd.common.dao.UserDao;
import com.wd.health_home_fragment.R;
import com.wd.health_home_fragment.activity.DiseaseActivity;
import com.wd.health_home_fragment.activity.EvaluatingActivity;
import com.wd.health_home_fragment.activity.InformationDetailsActivity;
import com.wd.health_home_fragment.activity.InformationMoreActivity;
import com.wd.health_home_fragment.activity.InterrogationActivity;
import com.wd.health_home_fragment.adapter.DivisionAdapter;
import com.wd.health_home_fragment.adapter.InformationAdapter;
import com.wd.health_home_fragment.adapter.InformationListAdapter;
import com.wd.health_home_fragment.presenter.HomePresenter;
import com.wd.health_my.MyActivity;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * <p>文件描述：<p>
 * <p>作者：吴新仲<p>
 * <p>创建时间：2019/12/5/005<p>
 * <p>更改时间：2019/12/5/005<p>
 */
public class HomeFragment extends BaseFragment<HomePresenter> implements Constraint.IHomeView, View.OnClickListener {

    private static final String TAG = "HomeFragment";

    private XBanner home_inquiry_xBanner;
    private LinearLayout home_inquiry_disease;
    private LinearLayout home_inquiry_drug;
    private RecyclerView home_inquiry_division;
    private SimpleDraweeView home_inquiry_evaluating;
    private RecyclerView home_inquiry_recycle;
    private RecyclerView home_inquiry_recycle_list;
    private TextView home_inquiry_more;
    private SimpleDraweeView home_inquiry_login;
    private LinearLayout home_inquiry_search;
    private ImageView home_inquiry_news;
    private DivisionAdapter mDivisionAdapter;
    private InformationListAdapter mInformationListAdapter;
    private InformationAdapter mInformationAdapter;
    private int mPosition;
    private String mName;


    @Override
    protected void initData() {

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 4);
        home_inquiry_division.setLayoutManager(gridLayoutManager);
        mDivisionAdapter = new DivisionAdapter();
        home_inquiry_division.setAdapter(mDivisionAdapter);
        mDivisionAdapter.setOnItemClickListener(new DivisionAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(getContext(), InterrogationActivity.class);
                intent.putExtra("id", position);
                startActivity(intent);
            }
        });


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        home_inquiry_recycle.setLayoutManager(linearLayoutManager);
        mInformationListAdapter = new InformationListAdapter();
        home_inquiry_recycle.setAdapter(mInformationListAdapter);
        mInformationListAdapter.setOnItemClickListener(new InformationListAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position, String name) {
                mPosition = position;
                mName = name;
                presenter.information(position, true);
            }
        });


        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getContext());
        linearLayoutManager1.setOrientation(LinearLayoutManager.VERTICAL);
        home_inquiry_recycle_list.setLayoutManager(linearLayoutManager1);


        mInformationAdapter = new InformationAdapter();
        home_inquiry_recycle_list.setAdapter(mInformationAdapter);
        mInformationAdapter.setOnItemClickListener(new InformationAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(getContext(), InformationDetailsActivity.class);
                intent.putExtra("infoId", position);
                startActivity(intent);
            }
        });


        presenter.informationList();
        presenter.division();
        presenter.banner();
    }

    @Override
    protected HomePresenter getPresenter() {
        return new HomePresenter();
    }

    @Override
    protected void initListener() {
        home_inquiry_disease.setOnClickListener(this);
        home_inquiry_drug.setOnClickListener(this);
        home_inquiry_more.setOnClickListener(this);
        home_inquiry_login.setOnClickListener(this);
        home_inquiry_evaluating.setOnClickListener(this);
        home_inquiry_news.setOnClickListener(this);

        SharedPreferences config = getContext().getSharedPreferences("config", MODE_PRIVATE);
        boolean isFirst = config.getBoolean("isFirst", false);
        if (isFirst) {

            DaoSession daoSession = DaoMaster.newDevSession(getContext(), UserDao.TABLENAME);
            UserDao userDao = daoSession.getUserDao();
            List<User> users = userDao.loadAll();

            String headPic = users.get(0).getHeadPic();

            Uri uri = Uri.parse(headPic);
            home_inquiry_login.setImageURI(uri);

        } else {
            Uri uri = Uri.parse("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=2060761043,284284863&fm=27&g=0.jpg");
            home_inquiry_login.setImageURI(uri);
        }

    }

    protected void initView(View view) {
        home_inquiry_xBanner = (XBanner) view.findViewById(R.id.home_inquiry_xBanner);
        home_inquiry_disease = (LinearLayout) view.findViewById(R.id.home_inquiry_disease);
        home_inquiry_drug = (LinearLayout) view.findViewById(R.id.home_inquiry_drug);
        home_inquiry_division = (RecyclerView) view.findViewById(R.id.home_inquiry_division);
        home_inquiry_evaluating = (SimpleDraweeView) view.findViewById(R.id.home_inquiry_evaluating);
        home_inquiry_recycle = (RecyclerView) view.findViewById(R.id.home_inquiry_recycle);
        home_inquiry_recycle_list = (RecyclerView) view.findViewById(R.id.home_inquiry_recycle_list);
        home_inquiry_more = (TextView) view.findViewById(R.id.home_inquiry_more);
        home_inquiry_login = (SimpleDraweeView) view.findViewById(R.id.home_inquiry_login);
        home_inquiry_search = (LinearLayout) view.findViewById(R.id.home_inquiry_search);
        home_inquiry_news = (ImageView) view.findViewById(R.id.home_inquiry_news);
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_home_layout;
    }

    @Override
    public void IHomeBannerSuccess(BannerBean bannerBean) {
        if (bannerBean.status.equals("0000")) {

            final List<BannerBean.ResultBean> result = bannerBean.result;

            home_inquiry_xBanner.setData(result, null);
//            final SimpleDraweeView img = home_inquiry_xBanner.findViewById(R.id.home_banner_img);
            home_inquiry_xBanner.setmAdapter(new XBanner.XBannerAdapter() {
                @Override
                public void loadBanner(XBanner banner, Object model, View view, int position) {

                    Glide.with(view.getContext()).load(result.get(position).imageUrl).into((ImageView) view);

//                    Uri uri = Uri.parse(result.get(position).imageUrl);

//                    img.setImageURI(uri);
                }
            });
            home_inquiry_xBanner.setPageTransformer(Transformer.Default);
            home_inquiry_xBanner.setPageChangeDuration(3000);
            home_inquiry_xBanner.startAutoPlay();


        }
    }

    @Override
    public void onResume() {
        super.onResume();
        home_inquiry_xBanner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        home_inquiry_xBanner.startAutoPlay();
    }

    @Override
    public void IHomeBannerError(String s) {
        Log.i(TAG, "IHomeBannerError: " + s);
    }

    @Override
    public void IDivisionSuccess(DivisionBean divisionBean) {
        if (divisionBean.status.equals("0000")) {
            mDivisionAdapter.addAll(divisionBean.result);
            mDivisionAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void IDivisionError(String s) {
        Log.i(TAG, "IDivisionError: " + s);
    }

    //健康资讯分类
    @Override
    public void IInformationListSuccess(InformationListBean informationListBean) {
        if (informationListBean.status.equals("0000")) {

            mInformationListAdapter.clear();
            mInformationListAdapter.addAll(informationListBean.result);
            mInformationListAdapter.notifyDataSetChanged();

        }
    }

    @Override
    public void IInformationListError(String s) {

    }

    //健康资讯
    @Override
    public void IInformationSuccess(InformationBean informationBean) {
        if (informationBean.status.equals("0000")) {

            mInformationAdapter.clear();
            mInformationAdapter.addAll(informationBean.result);
            mInformationAdapter.notifyDataSetChanged();

        }
    }

    @Override
    public void IInformationError(String s) {

    }

    @Override
    public void onClick(View v) {

        SharedPreferences config = getActivity().getSharedPreferences("config", MODE_PRIVATE);
        boolean isFirst = config.getBoolean("isFirst", false);

        if (v.getId() == R.id.home_inquiry_disease) {

            Intent intent = new Intent(getContext(), DiseaseActivity.class);
            intent.putExtra("id", 1);
            startActivity(intent);

        } else if (v.getId() == R.id.home_inquiry_drug) {
            Intent intent = new Intent(getContext(), DiseaseActivity.class);
            intent.putExtra("id", 2);
            startActivity(intent);

        } else if (v.getId() == R.id.home_inquiry_more) {

            Intent intent = new Intent(getContext(), InformationMoreActivity.class);
            intent.putExtra("id", mPosition);
            intent.putExtra("name", mName);
            startActivity(intent);

        } else if (v.getId() == R.id.home_inquiry_login) {

            startActivity(new Intent(getContext(), MyActivity.class));

        } else if (v.getId() == R.id.home_inquiry_evaluating) {

            if (isFirst) {
                startActivity(new Intent(getContext(), EvaluatingActivity.class));
            } else {
                Toast.makeText(getContext(), "请先登录", Toast.LENGTH_SHORT).show();
            }

        } else if (v.getId() == R.id.home_inquiry_news) {

            Toast.makeText(getContext(), "消息", Toast.LENGTH_SHORT).show();

        }
    }
}
