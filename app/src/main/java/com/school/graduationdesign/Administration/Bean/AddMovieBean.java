package com.school.graduationdesign.Administration.Bean;

import android.widget.ImageView;

import java.io.Serializable;

/**
 * @author 薄学林
 * @version 1.0
 * @describe
 * @date 2016/4/21
 */
public class AddMovieBean implements Serializable {
    private String adm_movie_list_num;
    private String adm_movie_list_des;
    private ImageView adm_movie_list_pic;
    private int id;

    public String getAdm_movie_list_name() {
        return adm_movie_list_num;
    }

    public void setAdm_movie_list_name(String adm_movie_list_name) {
        this.adm_movie_list_num = adm_movie_list_name;
    }

    public String getAdm_movie_list_des() {
        return adm_movie_list_des;
    }

    public void setAdm_movie_list_des(String adm_movie_list_des) {
        this.adm_movie_list_des = adm_movie_list_des;
    }

    public ImageView getAdm_movie_list_pic() {
        return adm_movie_list_pic;
    }

    public void setAdm_movie_list_pic(ImageView adm_movie_list_pic) {
        this.adm_movie_list_pic = adm_movie_list_pic;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
