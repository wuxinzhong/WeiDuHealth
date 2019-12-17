package com.wd.common.constraint;

import com.wd.common.bean.BannerBean;
import com.wd.common.bean.CommonDrugBean;
import com.wd.common.bean.DiseaseBean;
import com.wd.common.bean.DiseaseDetailsBean;
import com.wd.common.bean.DivisionBean;
import com.wd.common.bean.DoctorBean;
import com.wd.common.bean.DrugBean;
import com.wd.common.bean.DrugDetailsBean;
import com.wd.common.bean.EvaluatingBean;
import com.wd.common.bean.InformationBean;
import com.wd.common.bean.InformationDetailsBean;
import com.wd.common.bean.InformationListBean;
import com.wd.common.bean.LoginBean;
import com.wd.common.bean.RegressBean;
import com.wd.common.bean.SendEmailBean;

/**
 * <p>文件描述： 契约类   <p>
 * <p>作者：吴新仲<p>
 * <p>创建时间：2019/12/4/004<p>
 * <p>更改时间：2019/12/4/004<p>
 */
public interface Constraint {

    //首页一级页
    interface IHomeView extends IBaseView {
        //banner
        void IHomeBannerSuccess(BannerBean bannerBean);

        void IHomeBannerError(String s);

        //查询科室列表
        void IDivisionSuccess(DivisionBean divisionBean);

        void IDivisionError(String s);

        //健康资讯分类列表
        void IInformationListSuccess(InformationListBean informationListBean);

        void IInformationListError(String s);

        //健康资讯列表
        void IInformationSuccess(InformationBean informationBean);

        void IInformationError(String s);
    }

    interface IDivisionView extends IBaseView {
        //查询科室列表
        void IDivisionSuccess(DivisionBean divisionBean);

        void IDivisionError(String s);

        //查询医生列表
        void IDoctorSuccess(DoctorBean doctorBean);

        void IDoctorError(String s);
    }

    interface IInformationView extends IBaseView {
        //健康资讯列表
        void IInformationSuccess(InformationBean informationBean);

        void IInformationError(String s);
    }

    //健康测评
    interface IEvaluatingView extends IBaseView {
        void evaluatingSuccess(EvaluatingBean evaluatingBean);

        void evaluatingError(String s);
    }

    //首页知识库二级页
    interface IHomeTow extends IBaseView {
        //查询科室列表
        void IDivisionSuccess(DivisionBean divisionBean);

        void IDivisionError(String s);

        //根据科室查询对应病症
        void IDiseaseSuccess(DiseaseBean diseaseBean);

        void IDiseaseError(String s);

        //药品科室分类列表
        void IDrugSuccess(DrugBean drugBean);

        void IDrugError(String s);

        //根据药品类目查询常见药品
        void ICommonDrugSuccess(CommonDrugBean commonDrugBean);

        void ICommonDrugError(String s);
    }

    //首页知识库病症详情三级页面
    interface IDiseaseThree extends IBaseView {
        void IDiseaseSuccess(DiseaseDetailsBean diseaseDetailsBean);

        void IDiseaseError(String s);
    }

    //首页知识库病症详情三级页面
    interface IDrugThree extends IBaseView {
        void IDrugSuccess(DrugDetailsBean drugDetailsBean);

        void IDrugError(String s);
    }

    //首页资讯详情三级页面
    interface IInformationDetailView extends IBaseView {
        void IInformationDetailsSuccess(InformationDetailsBean informationDetailsBean);

        void IInformationDetailsError(String s);
    }


    //登录
    interface ILoginView extends IBaseView {
        void loginSuccess(LoginBean loginBean);

        void loginError(String s);
    }


    interface IRegressView extends IBaseView {
        //注册
        void regressSuccess(RegressBean regressBean);

        void regressError(String s);

        //发送邮箱验证码
        void sendEmailSuccess(SendEmailBean regressBean);

        void sendEmailError(String s);
    }

}
