package com.example.academia.firebase;

public class Message {
    String icon;
    String  title;
    String type;
    String image;
    String  des;
    String  date;


    public Message() {
    }

    public Message(String icon, String title, String type, String image, String des, String date) {
        this.icon = icon;
        this.title = title;
        this.type = type;
        this.image = image;
        this.des = des;
        this.date = date;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}