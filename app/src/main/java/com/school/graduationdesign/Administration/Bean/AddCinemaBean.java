package com.school.graduationdesign.Administration.Bean;

import android.widget.ImageView;

import java.io.Serializable;

/**
 * @author 薄学林
 * @version 1.0
 * @describe 添加影院实体类
 * @date 2016/4/20
 */
public class AddCinemaBean implements Serializable {
    private String adm_cinema_list_name;
    private String add_cinema_list_ad;
    private ImageView adm_cinema_list_pic;
    private int id;

    public ImageView getAdm_cinema_list_pic() {
        return adm_cinema_list_pic;
    }

    public void setAdm_cinema_list_pic(ImageView adm_cinema_list_pic) {
        this.adm_cinema_list_pic = adm_cinema_list_pic;
    }

    public String getAdm_cinema_list_name() {
        return adm_cinema_list_name;
    }

    public void setAdm_cinema_list_name(String adm_cinema_list_name) {
        this.adm_cinema_list_name = adm_cinema_list_name;
    }

    public String getAdd_cinema_list_ad() {
        return add_cinema_list_ad;
    }

    public void setAdd_cinema_list_ad(String add_cinema_list_ad) {
        this.add_cinema_list_ad = add_cinema_list_ad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
