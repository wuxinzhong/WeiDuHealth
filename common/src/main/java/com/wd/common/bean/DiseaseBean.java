package com.wd.common.bean;

import java.util.List;

/**
 * <p>文件描述： 根据科室查询对应病症  <p>
 * <p>作者：吴新仲<p>
 * <p>创建时间：2019/12/5/005<p>
 * <p>更改时间：2019/12/5/005<p>
 */
public class DiseaseBean {

    public String message;
    public String status;
    public List<ResultBean> result;

    public static class ResultBean {
        /**
         * departmentId : 2
         * id : 17
         * name : 颈椎病
         */

        public int departmentId;
        public int id;
        public String name;
    }
}
