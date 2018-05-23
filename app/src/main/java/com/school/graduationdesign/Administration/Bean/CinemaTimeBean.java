package com.school.graduationdesign.Administration.Bean;

import java.io.Serializable;

/**
 * @author 薄学林
 * @version 1.0
 * @describe
 * @date 2016/5/3
 */
public class CinemaTimeBean implements Serializable {
    private String adm_cinematime_list_name;
    private String adm_cinematime_list_time;
    private String adm_cinematime_list_price;
    private String adm_cinematime_list_num;

    public String getAdm_cinematime_list_price() {
        return adm_cinematime_list_price;
    }

    public void setAdm_cinematime_list_price(String adm_cinematime_list_price) {
        this.adm_cinematime_list_price = adm_cinematime_list_price;
    }

    public String getAdm_cinematime_list_num() {
        return adm_cinematime_list_num;
    }

    public void setAdm_cinematime_list_num(String adm_cinematime_list_num) {
        this.adm_cinematime_list_num = adm_cinematime_list_num;
    }

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAdm_cinematime_list_name() {
        return adm_cinematime_list_name;
    }

    public void setAdm_cinematime_list_name(String adm_cinematime_list_name) {
        this.adm_cinematime_list_name = adm_cinematime_list_name;
    }

    public String getAdm_cinematime_list_time() {
        return adm_cinematime_list_time;
    }

    public void setAdm_cinematime_list_time(String adm_cinematime_list_time) {
        this.adm_cinematime_list_time = adm_cinematime_list_time;
    }
}
