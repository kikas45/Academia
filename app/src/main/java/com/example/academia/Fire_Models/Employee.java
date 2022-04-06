package com.example.academia.Fire_Models;

public class Employee {
    String image, title, des;
    String key;

    public Employee() {
    }

    public Employee(String image, String title, String des) {
        this.image = image;
        this.title = title;
        this.des = des;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
