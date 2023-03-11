package com.zy.activity.bean;

public class BaseBean {
    private String title;
    private int type;


    public BaseBean(String title, int type) {
        this.title = title;
        this.type = type;
    }


    public String getTitle() {
        return title;
    }

    public int getType() {
        return type;
    }

}
