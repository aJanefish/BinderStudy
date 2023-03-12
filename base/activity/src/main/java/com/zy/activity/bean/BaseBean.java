package com.zy.activity.bean;

public class BaseBean {

    public static int COMMON = 0;
    public static int HEAD = 1;

    private int type = COMMON;
    private final String des;
    private int code;


    public BaseBean(String des, int code) {
        this.des = des;
        this.code = code;
    }

    public BaseBean(String des) {
        this.des = des;
        this.type = HEAD;
    }

    public int getType() {
        return type;
    }


    public String getDes() {
        return des;
    }

    public int getCode() {
        return code;
    }
}
