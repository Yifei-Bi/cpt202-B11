package com.example.finall.pojo;

import java.io.File;

public class Comments {

    public String text;
    //public String filepath;

    public int order_id;

    public int groomer_ranking;

    public int service_rating;

    public int shop_rating;

//    public int phone;

    public Comments(String text, int order_id, int groomer_ranking, int service_rating, int shop_rating) {
        this.text = text;
        this.order_id = order_id;
        this.groomer_ranking = groomer_ranking;
        this.service_rating = service_rating;
        this.shop_rating = shop_rating;
        //this.phone = phone;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getGroomer_ranking() {
        return groomer_ranking;
    }

    public void setGroomer_ranking(int groomer_ranking) {
        this.groomer_ranking = groomer_ranking;
    }

    public int getService_rating() {
        return service_rating;
    }

    public void setService_rating(int service_rating) {
        this.service_rating = service_rating;
    }

    public int getShop_rating() {
        return shop_rating;
    }

    public void setShop_rating(int shop_rating) {
        this.shop_rating = shop_rating;
    }

//    public int getPhone() {
//        return phone;
//    }
//
//    public void setPhone(int phone) {
//        this.phone = phone;
//    }
}
