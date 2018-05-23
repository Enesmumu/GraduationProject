package com.school.graduationdesign.Bean;

import java.io.Serializable;

/**
 * @author 薄学林
 * @version 1.0
 * @describe
 * @date 2016/4/6
 */
public class MovieBean implements Serializable {
    private String moviename_list;
    private String moviedes_list;
    private int movieids_list;

    public String getMoviename_list() {
        return moviename_list;
    }

    public void setMoviename_list(String moviename_list) {
        this.moviename_list = moviename_list;
    }

    public String getMoviedes_list() {
        return moviedes_list;
    }

    public void setMoviedes_list(String moviedes_list) {
        this.moviedes_list = moviedes_list;
    }

    public int getMovieids_list() {
        return movieids_list;
    }

    public void setMovieids_list(int movieids_list) {
        this.movieids_list = movieids_list;
    }
}
