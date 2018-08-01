package com.brandappz.alpcord.events.model;

public class Item {

    public String text1,subText;
    public Boolean isExpandable;

    Item(){

    }

    public Item(String text1, String subText, Boolean isExpandable) {
        this.text1 = text1;
        this.subText = subText;
        this.isExpandable = isExpandable;
    }

    public String getText1() {
        return text1;
    }

    public void setText1(String text1) {
        this.text1 = text1;
    }

    public String getSubText() {
        return subText;
    }

    public void setSubText(String subText) {
        this.subText = subText;
    }

    public Boolean getExpandable() {
        return isExpandable;
    }

    public void setExpandable(Boolean expandable) {
        isExpandable = expandable;
    }
}
