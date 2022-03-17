package com.phonghtse140633.linlus_pg.model;

public class Customer {
    private int id;
    private String name;
    private int avatarImg;

    public Customer(int id, String name, int avatarImg) {
        this.id = id;
        this.name = name;
        this.avatarImg = avatarImg;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAvatarImg() {
        return avatarImg;
    }

    public void setAvatarImg(int avatarImg) {
        this.avatarImg = avatarImg;
    }
}
