package com.zy.animation.bean;

public class AnimationMenuBean {

    private String title;
    private int type;


    public AnimationMenuBean(String title, int type) {
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