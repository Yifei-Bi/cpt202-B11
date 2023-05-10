package com.example.finall.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.example.finall.pojo.Card;

import java.util.List;

@Mapper
public interface CardMapper {

    //创建一个Card int addBook(books books);
    int addCard(Card card);


    //删除一个Card int addBook(books books);
    int deleteCardById(int card_id);


    //更新一个Card
    int updateCard(Card card);

    //查询一个Card
    Card QueryCardById(int phone);

    List<Card> IsVip(int phone);

}
