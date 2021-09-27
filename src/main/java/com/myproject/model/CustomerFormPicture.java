package com.myproject.model;

import org.springframework.web.multipart.MultipartFile;

public class CustomerFormPicture {
    private Long id;
    private String nameCustomer;
    private MultipartFile img;
    private Province province;

    public CustomerFormPicture() {
    }

    public CustomerFormPicture(Long id, String nameCustomer, MultipartFile img, Province province) {
        this.id = id;
        this.nameCustomer = nameCustomer;
        this.img = img;
        this.province = province;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameCustomer() {
        return nameCustomer;
    }

    public void setNameCustomer(String nameCustomer) {
        this.nameCustomer = nameCustomer;
    }

    public MultipartFile getImg() {
        return img;
    }

    public void setImg(MultipartFile img) {
        this.img = img;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }
}
