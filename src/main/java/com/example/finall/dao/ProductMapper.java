package com.example.finall.dao;

import com.example.finall.pojo.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {
    int addProduct(Product product);


    //删除一个Card int addBook(books books);
    //int deleteCardById(int card_id);
    int deleteProductByname(String productName);

    //更新一个Card
    int updateProduct(Product product);

    //查询一个Card
    //Product QueryProductByName(String product_name);

    Product QueryProductByName(String name);// by yin
    List<Product> queryProduct();//by yin
}
