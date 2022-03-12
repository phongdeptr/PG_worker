package com.phonghtse140633.linlus_pg.model;

import java.util.List;

public class PhotoService {
    private Long id;
    private String name;
    private String bundleLabel;
    private float price;
    private float rating;
    private int representativeImg;
    private List<Integer> bannerUrls;
    private int idCategory;

    public PhotoService() {
    }

    public PhotoService(Long id, String name, String bundleLabel, float price, float rating, int representativeImg, List<Integer> bannerUrls, int idCategory) {
        this.id = id;
        this.name = name;
        this.bundleLabel = bundleLabel;
        this.price = price;
        this.rating = rating;
        this.representativeImg = representativeImg;
        this.bannerUrls = bannerUrls;
        this.idCategory = idCategory;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBundleLabel() {
        return bundleLabel;
    }

    public void setBundleLabel(String bundleLabel) {
        this.bundleLabel = bundleLabel;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getRepresentativeImg() {
        return representativeImg;
    }

    public void setRepresentativeImg(int representativeImg) {
        this.representativeImg = representativeImg;
    }

    public List<Integer> getBannerUrls() {
        return bannerUrls;
    }

    public void setBannerUrls(List<Integer> bannerUrls) {
        this.bannerUrls = bannerUrls;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }
}
