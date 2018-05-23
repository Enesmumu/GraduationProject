package com.school.graduationdesign.Bean;

import java.io.Serializable;

/**
 * @author 薄学林
 * @version 1.0
 * @describe
 * @date 2016/4/6
 */
public class YingYuanBean implements Serializable {
    private String dianyingname_list;
    private String dianyingdes_list;
    private int dianyingids_list;

    public String getDianyingname_list() {
        return dianyingname_list;
    }

    public void setDianyingname_list(String dianyingname_list) {
        this.dianyingname_list = dianyingname_list;
    }

    public String getDianyingdes_list() {
        return dianyingdes_list;
    }

    public void setDianyingdes_list(String dianyingdes_list) {
        this.dianyingdes_list = dianyingdes_list;
    }

    public int getDianyingids_list() {
        return dianyingids_list;
    }

    public void setDianyingids_list(int dianyingids_list) {
        this.dianyingids_list = dianyingids_list;
    }
}
