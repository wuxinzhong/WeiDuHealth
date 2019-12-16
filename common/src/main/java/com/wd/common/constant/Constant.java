package com.wd.common.constant;

import com.wd.common.bean.BannerBean;
import com.wd.common.bean.CommonDrugBean;
import com.wd.common.bean.DiseaseBean;
import com.wd.common.bean.DiseaseDetailsBean;
import com.wd.common.bean.DivisionBean;
import com.wd.common.bean.DrugBean;
import com.wd.common.bean.DrugDetailsBean;
import com.wd.common.bean.EvaluatingBean;
import com.wd.common.bean.InformationBean;
import com.wd.common.bean.InformationDetailsBean;
import com.wd.common.bean.InformationListBean;
import com.wd.common.bean.LoginBean;
import com.wd.common.bean.RegressBean;
import com.wd.common.bean.SendEmailBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * <p>文件描述： 接口  <p>
 * <p>作者：吴新仲<p>
 * <p>创建时间：2019/12/4/004<p>
 * <p>更改时间：2019/12/4/004<p>
 */
public interface Constant {

    //banner接口
    @GET("share/v1/bannersShow")
    Observable<BannerBean> BANNER_BEAN();

    //查询科室列表
    @GET("share/knowledgeBase/v1/findDepartment")
    Observable<DivisionBean> DIVISION_BEAN();

    //根据科室查询对应病症
    @GET("share/knowledgeBase/v1/findDiseaseCategory")
    Observable<DiseaseBean> DISEASE_BEAN(@Query("departmentId") int departmentId);

    //药品科室分类列表
    @GET("share/knowledgeBase/v1/findDrugsCategoryList")
    Observable<DrugBean> DRUG_BEAN();

    //根据药品类目查询常见药品
    @GET("share/knowledgeBase/v1/findDrugsKnowledgeList")
    Observable<CommonDrugBean> COMMON_DRUG_BEAN(@Query("drugsCategoryId") int drugsCategoryId,
                                                @Query("page") int page,
                                                @Query("count") int count);

    //查询症状详情
    @GET("share/knowledgeBase/v1/findDiseaseKnowledge")
    Observable<DiseaseDetailsBean> DISEASE_DETAILS_BEAN(@Query("id") int id);


    //查询药品详情
    @GET("share/knowledgeBase/v1/findDrugsKnowledge")
    Observable<DrugDetailsBean> DRUG_DETAILS_BEAN(@Query("id") int id);

    //健康评测
    @GET("user/verify/v1/userHealthTest")
    Observable<EvaluatingBean> EVALUATING_BEAN(@Header("userId") int userId,
                                               @Header("sessionId") String sessionId);

    //健康资讯分类列表
    @GET("share/information/v1/findInformationPlateList")
    Observable<InformationListBean> INFORMATION_LIST_BEAN();

    //健康资讯列表
    @GET("share/information/v1/findInformationList")
    Observable<InformationBean> INFORMATION_BEAN(@Query("plateId") int plateId,
                                                 @Query("page") int page,
                                                 @Query("count") int count);

    //资讯详情
    @GET("share/information/v1/findInformation")
    Observable<InformationDetailsBean> INFORMATION_DETAILS_BEAN(@Query("userId") int userId,
                                                                @Query("sessionId") String sessionId,
                                                                @Query("infoId") int infoId);


    //登录
    @FormUrlEncoded
    @POST("user/v1/login")
    Observable<LoginBean> LOGIN_BEAN(@Field("email") String email,
                                     @Field("pwd") String pwd);

    //注册
    @FormUrlEncoded
    @POST("user/v1/register")
    Observable<RegressBean> REGRESS_BEAN(@Field("email") String email,
                                         @Field("code") String code,
                                         @Field("pwd1") String pwd1,
                                         @Field("pwd2") String pwd2,
                                         @Field("invitationCode") String invitationCode);

    //获取邮箱验证码
    @FormUrlEncoded
    @POST("user/v1/sendOutEmailCode")
    Observable<SendEmailBean> SEND_EMAIL_BEAN(@Field("email") String email);

}
