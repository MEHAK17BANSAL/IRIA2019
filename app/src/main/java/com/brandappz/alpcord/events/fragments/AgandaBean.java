package com.brandappz.alpcord.events.fragments;

/**
 * Created by perveen akhter on 22-03-2018.
 */

public class AgandaBean {
    String time;
    String title;
    String desc;
    String desc2;

    public String getDesc2() {
        return desc2;
    }

    public void setDesc2(String desc2) {
        this.desc2 = desc2;
    }

    public AgandaBean(String title) {
        this.title = title;


    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
