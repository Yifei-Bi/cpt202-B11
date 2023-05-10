package com.example.finall.pojo;

public class Shop {
    public String shop_name;
    public String address;
    public String phone;

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Shop(String shop_name, String address, String phone) {
        this.shop_name = shop_name;
        this.address = address;
        this.phone = phone;
    }
}
