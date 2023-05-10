package com.example.finall.pojo;



public class books {
    private int id;
    private String bookname;
    private int bookCounts;
    private String details;

    public int getId() {
        return id;
    }

    public String getBookname() {
        return bookname;
    }

    public int getBookCounts() {
        return bookCounts;
    }

    public String getDetails() {
        return details;
    }

    public books(int id, String bookname, int bookCounts, String details) {
        this.id = id;
        this.bookname = bookname;
        this.bookCounts = bookCounts;
        this.details = details;
    }
}
