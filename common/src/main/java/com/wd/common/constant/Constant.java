package com.wd.common.constant;

import com.wd.common.bean.BannerBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

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

}
