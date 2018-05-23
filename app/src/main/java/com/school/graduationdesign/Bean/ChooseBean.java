package com.school.graduationdesign.Bean;

import java.io.Serializable;

/**
 * @author 薄学林
 * @version 1.0
 * @describe
 * @date 2016/5/6
 */
public class ChooseBean implements Serializable {
    private String xuanzhong;
    private String zuoweihao;

    public String getZuoweihao() {
        return zuoweihao;
    }

    public void setZuoweihao(String zuoweihao) {
        this.zuoweihao = zuoweihao;
    }

    public String getXuanzhong() {
        return xuanzhong;
    }

    public void setXuanzhong(String xuanzhong) {
        this.xuanzhong = xuanzhong;
    }
}
