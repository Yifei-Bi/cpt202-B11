package com.example.finall.pojo;

public class Service {
    public String type_name;

    public int price;

    public String getDescription() {
        return description;
    }

    public String description;

    public String getType() {
        return type_name;
    }

    public void setType(String type) {
        this.type_name = type;
    }



    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Service(String type, String description, int price) {
        this.type_name = type;
        this.description = description;
        this.price = price;
    }

    int vipPrice;

    public int getVipPrice() {
        return vipPrice;
    }

    public void setVipPrice(int vipPrice) {
        this.vipPrice = vipPrice;
    }


}
