package com.wd.health_login_and_regress.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.wd.common.base.BaseActivity;
import com.wd.common.bean.RegressBean;
import com.wd.common.bean.SendEmailBean;
import com.wd.common.constraint.Constraint;
import com.wd.health_login_and_regress.R;
import com.wd.health_login_and_regress.RsaCoder;
import com.wd.health_login_and_regress.presenter.RegressPresenter;
import com.wd.health_login_and_regress.toast.CustomToast;

public class RegressActivity extends BaseActivity<RegressPresenter> implements View.OnClickListener, Constraint.IRegressView {

    private EditText my_regress_email;
    private Button my_regress_huo_email;
    private EditText my_regress_email_code;
    private CheckBox my_regress_unlock;
    private EditText my_regress_pwd;
    private CheckBox my_regress_hide_eye;
    private CheckBox my_regress_unlock1;
    private EditText my_regress_pwd1;
    private CheckBox my_regress_hide_eye1;
    private EditText my_regress_code;
    private Button my_regress_btn;
    private String mCode;
    private String mPwd;
    private String mPwd1;
    private String mCode1;
    private String mEmail1;
    private CustomToast toast;


    private static final String TAG = "RegressActivity";

    @Override
    protected void initData() {


        my_regress_hide_eye.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //如果选中，显示密码
                    my_regress_unlock.setChecked(isChecked);
                    my_regress_pwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    //否则隐藏密码
                    my_regress_unlock.setChecked(isChecked);
                    my_regress_pwd.setTransformationMethod(PasswordTransformationMethod.getInstance());

                }
            }
        });

        my_regress_hide_eye1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //如果选中，显示密码
                    my_regress_unlock1.setChecked(isChecked);
                    my_regress_pwd1.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    //否则隐藏密码
                    my_regress_unlock1.setChecked(isChecked);
                    my_regress_pwd1.setTransformationMethod(PasswordTransformationMethod.getInstance());

                }
            }
        });

    }

    @Override
    protected RegressPresenter getpresenter() {
        return new RegressPresenter();
    }

    protected void initView() {
        my_regress_email = (EditText) findViewById(R.id.my_regress_email);
        my_regress_huo_email = (Button) findViewById(R.id.my_regress_huo_email);
        my_regress_email_code = (EditText) findViewById(R.id.my_regress_email_code);
        my_regress_unlock = (CheckBox) findViewById(R.id.my_regress_unlock);
        my_regress_pwd = (EditText) findViewById(R.id.my_regress_pwd);
        my_regress_hide_eye = (CheckBox) findViewById(R.id.my_regress_hide_eye);
        my_regress_unlock1 = (CheckBox) findViewById(R.id.my_regress_unlock1);
        my_regress_pwd1 = (EditText) findViewById(R.id.my_regress_pwd1);
        my_regress_hide_eye1 = (CheckBox) findViewById(R.id.my_regress_hide_eye1);
        my_regress_code = (EditText) findViewById(R.id.my_regress_code);
        my_regress_btn = (Button) findViewById(R.id.my_regress_btn);

        my_regress_huo_email.setOnClickListener(this);
        my_regress_btn.setOnClickListener(this);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected int initLayout() {
        return R.layout.activity_regress;
    }


    public void toastMessage(String content) {
        if (toast != null) {
            toast.hide();
        }
        toast = new CustomToast(RegressActivity.this,
                (ViewGroup) this.findViewById(R.id.toast_custom_parent));
        toast.show(content, 5000);
    }


    //邮箱验证
    public static boolean isEmail(String strEmail) {
        String strPattern = "^[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]@[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]$";
        if (TextUtils.isEmpty(strPattern)) {
            return false;
        } else {
            return strEmail.matches(strPattern);
        }
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.my_regress_huo_email) {
            mEmail1 = my_regress_email.getText().toString().trim();
            if (!TextUtils.isEmpty(mEmail1)) {
                isEmail(mEmail1);
                presenter.sendEmail(mEmail1);
                my_regress_huo_email.setText("重新获取");
            } else {
                toastMessage("邮箱不能为空");
            }

        } else if (v.getId() == R.id.my_regress_btn) {


            try {
                mCode = my_regress_email_code.getText().toString().trim();
                mPwd = my_regress_pwd.getText().toString().trim();
                mPwd1 = my_regress_pwd1.getText().toString().trim();
                mCode1 = my_regress_code.getText().toString().trim();


                if (TextUtils.isEmpty(mEmail1) || TextUtils.isEmpty(mCode) || TextUtils.isEmpty(mPwd) || TextUtils.isEmpty(mPwd1)) {
                    toastMessage("不能为空");

                } else {

                    if (!mPwd.equals(mPwd1)) {
                        toastMessage("两次输入密码不一样");
                    } else {
                        isEmail(mEmail1);
                        String s = RsaCoder.encryptByPublicKey(mPwd);
                        presenter.regress(mEmail1, mCode, s, s, mCode1);
                    }
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void regressSuccess(RegressBean regressBean) {

        if (regressBean.status.equals("0000")) {

//            toastMessage(regressBean.message);
            Toast.makeText(this, regressBean.message, Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    @Override
    public void regressError(String s) {
        Log.i(TAG, "regressError: " + s);
    }

    @Override
    public void sendEmailSuccess(SendEmailBean regressBean) {
        if (regressBean.message.equals("0000")) {
//            toastMessage(regressBean.message);
            Toast.makeText(this, regressBean.message, Toast.LENGTH_SHORT).show();
        } else {
//            toastMessage(regressBean.message);
            Toast.makeText(this, regressBean.message, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void sendEmailError(String s) {
        Log.i(TAG, "sendEmailError: " + s);
    }
}
