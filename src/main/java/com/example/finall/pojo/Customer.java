package com.example.finall.pojo;

import java.io.Serializable;

public class Customer implements Serializable {
    private static final long serialVersionUID = 1L;
    public String user_name;
    public String password;
    //public int balance;
    //public int  vip_level;


    public int phone;

    public Customer(String user_name, String password,int phone) {
        this.user_name = user_name;
        this.password = password;
        //this.vip_level = vip_level;

        this.phone = phone;
    }








    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



//    //public int getVip() {
//        return vip_level;
//    }
//
//    public void setVip(int vip_level) {
//        this.vip_level = vip_level;
//    }
}
