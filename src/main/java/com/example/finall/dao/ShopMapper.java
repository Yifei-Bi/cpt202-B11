package com.example.finall.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.example.finall.pojo.Shop;
@Mapper
public interface ShopMapper {

    int addShop(Shop shop);


    int updateShop(Shop shop);

}
