package com.wd.common.bean;

import java.util.List;

/**
 * <p>文件描述： 药品科室分类列表  <p>
 * <p>作者：吴新仲<p>
 * <p>创建时间：2019/12/5/005<p>
 * <p>更改时间：2019/12/5/005<p>
 */
public class DrugBean {

    public String message;
    public String status;
    public List<ResultBean> result;

    public static class ResultBean {
        /**
         * id : 1
         * name : 感冒用药
         * rank : 1
         */

        public int id;
        public String name;
        public int rank;
    }
}
