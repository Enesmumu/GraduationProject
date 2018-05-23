package com.school.graduationdesign.Bean;

import java.io.Serializable;

/**
 * @author 薄学林
 * @version 1.0
 * @describe 影院封装类
 * @date 2016/3/31
 */
public class CinemaBean implements Serializable {
    private String cinemaname_list;
    private String cinemaadd_list;
    private int cinemaids_list;

    public String getCinemaname_list() {
        return cinemaname_list;
    }

    public void setCinemaname_list(String cinemaname_list) {
        this.cinemaname_list = cinemaname_list;
    }

    public String getCinemaadd_list() {
        return cinemaadd_list;
    }

    public void setCinemaadd_list(String cinemaadd_list) {
        this.cinemaadd_list = cinemaadd_list;
    }

    public int getCinemaids_list() {
        return cinemaids_list;
    }

    public void setCinemaids_list(int cinemaids_list) {
        this.cinemaids_list = cinemaids_list;
    }
}
