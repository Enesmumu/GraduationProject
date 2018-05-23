package com.school.graduationdesign.Bean;

import java.io.Serializable;

/**
 * @author 薄学林
 * @version 1.0
 * @describe 座位选择封装类
 * @date 2016/4/25
 */
public class GridChooseBean implements Serializable {
    private String gv_seat;
    private String gv_num;

    public String getGv_num() {
        return gv_num;
    }

    public void setGv_num(String gv_num) {
        this.gv_num = gv_num;
    }

    public String getGv_seat() {
        return gv_seat;
    }

    public void setGv_seat(String gv_seat) {
        this.gv_seat = gv_seat;
    }
}
