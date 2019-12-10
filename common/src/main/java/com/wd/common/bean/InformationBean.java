package com.wd.common.bean;

import java.util.List;

/**
 * <p>文件描述： 健康资讯列表  <p>
 * <p>作者：吴新仲<p>
 * <p>创建时间：2019/12/7/007<p>
 * <p>更改时间：2019/12/7/007<p>
 */
public class InformationBean {

    public String message;
    public String status;
    public List<ResultBean> result;

    public static class ResultBean {
        /**
         * id : 21
         * plateId : 2
         * releaseTime : 1569464574000
         * source : @weidu
         * thumbnail : https://jkcdn.pajk.com.cn/image/T1zabSBmEv1RCvBVdK
         * title : 胖子每天去操场跑10圈不吃零食，3个月后身体有何变化？
         */

        public int id;
        public int plateId;
        public long releaseTime;
        public String source;
        public String thumbnail;
        public String title;
    }
}
