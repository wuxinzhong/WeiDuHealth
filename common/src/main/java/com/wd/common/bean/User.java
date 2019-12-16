package com.wd.common.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * <p>文件描述：<p>
 * <p>作者：吴新仲<p>
 * <p>创建时间：2019/12/12/012<p>
 * <p>更改时间：2019/12/12/012<p>
 */


@Entity
public class User {

    @Id
    public Long id;
    public int age;
    public String email;
    public String headPic;
    public int height;
    public int userId;
    public String nickName;
    public String sessionId;
    public int sex;
    public String userName;
    public int weight;
    public int whetherBingWeChat;
    @Generated(hash = 1904849858)
    public User(Long id, int age, String email, String headPic, int height,
            int userId, String nickName, String sessionId, int sex, String userName,
            int weight, int whetherBingWeChat) {
        this.id = id;
        this.age = age;
        this.email = email;
        this.headPic = headPic;
        this.height = height;
        this.userId = userId;
        this.nickName = nickName;
        this.sessionId = sessionId;
        this.sex = sex;
        this.userName = userName;
        this.weight = weight;
        this.whetherBingWeChat = whetherBingWeChat;
    }
    @Generated(hash = 586692638)
    public User() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public int getAge() {
        return this.age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getEmail() {
        return this.email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getHeadPic() {
        return this.headPic;
    }
    public void setHeadPic(String headPic) {
        this.headPic = headPic;
    }
    public int getHeight() {
        return this.height;
    }
    public void setHeight(int height) {
        this.height = height;
    }
    public int getUserId() {
        return this.userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public String getNickName() {
        return this.nickName;
    }
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    public String getSessionId() {
        return this.sessionId;
    }
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
    public int getSex() {
        return this.sex;
    }
    public void setSex(int sex) {
        this.sex = sex;
    }
    public String getUserName() {
        return this.userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public int getWeight() {
        return this.weight;
    }
    public void setWeight(int weight) {
        this.weight = weight;
    }
    public int getWhetherBingWeChat() {
        return this.whetherBingWeChat;
    }
    public void setWhetherBingWeChat(int whetherBingWeChat) {
        this.whetherBingWeChat = whetherBingWeChat;
    }

}
