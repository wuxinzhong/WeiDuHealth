package com.wd.common.constraint;

import com.wd.common.bean.BannerBean;

/**
 * <p>文件描述： 契约类   <p>
 * <p>作者：吴新仲<p>
 * <p>创建时间：2019/12/4/004<p>
 * <p>更改时间：2019/12/4/004<p>
 */
public interface Constraint {

    interface IHomeView extends IBaseView{
        //banner
        void IHomeBannerSuccess(BannerBean bannerBean);
        void IHomeBannerError(String s);
    }

}
