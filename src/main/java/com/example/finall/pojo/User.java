package com.example.finall.pojo;

public class User {
    public String user_name;
    public String password;
    //public int balance;
    public int  vip_level;
    public Card  Card;

    public User(String user_name, String password, int vip_level, Card card) {
        this.user_name = user_name;
        this.password = password;
        this.vip_level = vip_level;
        this.Card = card;
    }

    public Card getCard() {
        return Card;
    }

    public void setCard(Card card) {
        Card = card;
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

//    public int getBalance() {
//        return balance;
//    }
//
//    public void setBalance(int balance) {
//        this.balance = balance;
//    }

    public int getVip() {
        return vip_level;
    }

    public void setVip(int vip_level) {
        this.vip_level = vip_level;
    }
}
