package com.wd.common.bean;

import java.util.List;

/**
 * <p>文件描述： 科室列表  <p>
 * <p>作者：吴新仲<p>
 * <p>创建时间：2019/12/5/005<p>
 * <p>更改时间：2019/12/5/005<p>
 */
public class DivisionBean {

    public String message;
    public String status;
    public List<ResultBean> result;

    public static class ResultBean {

        public String departmentName;
        public int id;
        public String pic;
        public int rank;
    }
}
