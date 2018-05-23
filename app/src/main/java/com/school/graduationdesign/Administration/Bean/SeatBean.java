package com.school.graduationdesign.Administration.Bean;

import java.io.Serializable;

/**
 * @author 薄学林
 * @version 1.0
 * @describe
 * @date 2016/5/5
 */
public class SeatBean implements Serializable{
    private String adm_seat_list_status;
    private String adm_seat_list_num;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAdm_seat_list_status() {
        return adm_seat_list_status;
    }

    public void setAdm_seat_list_status(String adm_seat_list_status) {
        this.adm_seat_list_status = adm_seat_list_status;
    }

    public String getAdm_seat_list_num() {
        return adm_seat_list_num;
    }

    public void setAdm_seat_list_num(String adm_seat_list_num) {
        this.adm_seat_list_num = adm_seat_list_num;
    }
}
