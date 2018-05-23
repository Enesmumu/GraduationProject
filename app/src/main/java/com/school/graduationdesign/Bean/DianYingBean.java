package com.school.graduationdesign.Bean;

import java.io.Serializable;

/**
 * @author 薄学林
 * @version 1.0
 * @describe
 * @date 2016/4/6
 */
public class DianYingBean implements Serializable {
    private String yingyuanname_list;
    private String yingyuanadd_list;

    public String getYingyuanname_list() {
        return yingyuanname_list;
    }

    public void setYingyuanname_list(String yingyuanname_list) {
        this.yingyuanname_list = yingyuanname_list;
    }

    public String getYingyuanadd_list() {
        return yingyuanadd_list;
    }

    public void setYingyuanadd_list(String yingyuanadd_list) {
        this.yingyuanadd_list = yingyuanadd_list;
    }
}
