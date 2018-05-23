package com.school.graduationdesign.Administration.Bean;

import java.io.Serializable;

/**
 * @author 薄学林
 * @version 1.0
 * @describe
 * @date 2016/4/26
 */
public class UserBean implements Serializable {
    private String adm_user_list_num;
    private String adm_user_list_password;
    private int id;

    public String getAdm_user_list_num() {
        return adm_user_list_num;
    }

    public void setAdm_user_list_num(String adm_user_list_num) {
        this.adm_user_list_num = adm_user_list_num;
    }

    public String getAdm_user_list_password() {
        return adm_user_list_password;
    }

    public void setAdm_user_list_password(String adm_user_list_password) {
        this.adm_user_list_password = adm_user_list_password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
