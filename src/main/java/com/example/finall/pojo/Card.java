package com.example.finall.pojo;

public class Card {
    public int card_id;
    public int balance;


    public Card(int card_id, int balance) {
        this.card_id = card_id;
        this.balance = balance;
    }

    public int getCard_id() {
        return card_id;
    }

    public void setCard_id(int card_id) {
        this.card_id = card_id;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
