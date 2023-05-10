package com.example.finall.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;
    private String productName;
    private byte[] productPicture;
    private int price;

    private String description;

    private String filename;
    private String productPicture2;

    public Product(String productName, int price, String description, byte[] productPicture) {
        this.productName = productName;
        this.productPicture = productPicture;
        this.price = price;
        this.description = description;
    }

}
