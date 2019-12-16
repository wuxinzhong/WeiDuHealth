package com.wd.common.bean;

/**
 * <p>文件描述：  登录  <p>
 * <p>作者：吴新仲<p>
 * <p>创建时间：2019/12/11/011<p>
 * <p>更改时间：2019/12/11/011<p>
 */
public class LoginBean {

    public ResultBean result;
    public String message;
    public String status;

    public static class ResultBean {
        /**
         * age : 0
         * email : 1527184583@qq.com
         * headPic : http://172.17.8.100/images/health/user/head_pic/default/default_head_5.jpg
         * height : 0
         * id : 431
         * jiGuangPwd : R+0jdN3P4MXHPMFVe9cX5MbX5ulIXHJkfigPLKEeTBY5lUgxJWUNg0js1oGtbsKiLFL4ScqdmUbtHXIfrgQnWrwTNjf09OJLycbeJ+ka4+CV7I1eEqG8DtZPnQoCyxjoYMjO4soDl6EX9YgqaZp3DlUH4pXrYHYz58YyFkSeJEk=
         * nickName : Vs_LQBIY
         * sessionId : 1576025988671431
         * sex : 1
         * userName : JFAmPL1527184583
         * weight : 0
         * whetherBingWeChat : 2
         */

        public int age;
        public String email;
        public String headPic;
        public int height;
        public int id;
        public String jiGuangPwd;
        public String nickName;
        public String sessionId;
        public int sex;
        public String userName;
        public int weight;
        public int whetherBingWeChat;
    }
}
