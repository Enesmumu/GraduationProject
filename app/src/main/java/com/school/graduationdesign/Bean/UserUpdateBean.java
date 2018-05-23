package com.school.graduationdesign.Bean;

import java.io.Serializable;

/**
 * @author 薄学林
 * @version 1.0
 * @describe
 * @date 2016/5/16
 */
public class UserUpdateBean implements Serializable {
    private String user_nick;
    private String user_des;
    private String user_phone;
    private String user_school;
    private int id;

    public String getUser_nick() {
        return user_nick;
    }

    public void setUser_nick(String user_nick) {
        this.user_nick = user_nick;
    }

    public String getUser_des() {
        return user_des;
    }

    public void setUser_des(String user_des) {
        this.user_des = user_des;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getUser_school() {
        return user_school;
    }

    public void setUser_school(String user_school) {
        this.user_school = user_school;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
