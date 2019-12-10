package com.wd.common.bean;

import java.util.List;

/**
 * <p>文件描述： 健康资讯分类列表  <p>
 * <p>作者：吴新仲<p>
 * <p>创建时间：2019/12/7/007<p>
 * <p>更改时间：2019/12/7/007<p>
 */
public class InformationListBean {

    public String message;
    public String status;
    public List<ResultBean> result;

    public static class ResultBean {
        /**
         * id : 1
         * name : 健康养生
         * sort : 1
         */

        public int id;
        public String name;
        public int sort;
    }
}
