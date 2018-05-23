package com.school.graduationdesign.Bean;

import java.io.Serializable;

/**
 * @author 薄学林
 * @version 1.0
 * @describe
 * @date 2016/4/19
 */
public class YingYuanCinemaTimeBean implements Serializable {
    private String yingyuan_cinematimelist_name;
    private String yingyuan_cinematimelist_time;
    private String yingyuan_cinematimelist_price;
    private String yingyuan_cinematimelist_num;

    public String getYingyuan_cinematimelist_price() {
        return yingyuan_cinematimelist_price;
    }

    public void setYingyuan_cinematimelist_price(String yingyuan_cinematimelist_price) {
        this.yingyuan_cinematimelist_price = yingyuan_cinematimelist_price;
    }

    public String getYingyuan_cinematimelist_num() {
        return yingyuan_cinematimelist_num;
    }

    public void setYingyuan_cinematimelist_num(String yingyuan_cinematimelist_num) {
        this.yingyuan_cinematimelist_num = yingyuan_cinematimelist_num;
    }

    public String getYingyuan_cinematimelist_name() {
        return yingyuan_cinematimelist_name;
    }

    public void setYingyuan_cinematimelist_name(String yingyuan_cinematimelist_name) {
        this.yingyuan_cinematimelist_name = yingyuan_cinematimelist_name;
    }

    public String getYingyuan_cinematimelist_time() {
        return yingyuan_cinematimelist_time;
    }

    public void setYingyuan_cinematimelist_time(String yingyuan_cinematimelist_time) {
        this.yingyuan_cinematimelist_time = yingyuan_cinematimelist_time;
    }
}
