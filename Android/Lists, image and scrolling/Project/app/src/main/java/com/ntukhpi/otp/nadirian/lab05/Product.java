package com.ntukhpi.otp.nadirian.lab05;

import java.util.ArrayList;

public class Product  {

    String name;
    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    String surname;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getImage() {
        return image;
    }
    public void setImage(int image) {
        this.image = image;
    }
    public boolean isBox() {
        return box;
    }
    public void setBox(boolean box) {
        this.box = box;
    }
    int image;
    boolean box;

    public Product(String name, String surname, int image, boolean box) {
        this.name = name;
        this.surname = surname;
        this.image = image;
        this.box = box;
    }
    public Product() {

    }

}
