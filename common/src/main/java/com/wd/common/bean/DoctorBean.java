package com.wd.common.bean;

import java.util.List;

/**
 * <p>文件描述： 医生列表  <p>
 * <p>作者：吴新仲<p>
 * <p>创建时间：2019/12/16/016<p>
 * <p>更改时间：2019/12/16/016<p>
 */
public class DoctorBean {

    public String message;
    public String status;
    public List<ResultBean> result;

    public static class ResultBean {
        /**
         * badNum : 0
         * doctorId : 183
         * doctorName : 李晓明
         * imagePic : http://172.17.8.100/images/health/doctor/system_image_pic/system_image2.jpg
         * inauguralHospital : 北京和谐
         * jobTitle : 主治医师
         * praise : 0.00%
         * praiseNum : 0
         * serverNum : 0
         * servicePrice : 500
         */

        public int badNum;
        public int doctorId;
        public String doctorName;
        public String imagePic;
        public String inauguralHospital;
        public String jobTitle;
        public String praise;
        public int praiseNum;
        public int serverNum;
        public int servicePrice;
    }
}
