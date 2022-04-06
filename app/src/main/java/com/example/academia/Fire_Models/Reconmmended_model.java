package com.example.academia.Fire_Models;

public class Reconmmended_model {

    private String image, title, des;

    public Reconmmended_model() {
    }

    public Reconmmended_model(String image, String title, String des) {
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
}
