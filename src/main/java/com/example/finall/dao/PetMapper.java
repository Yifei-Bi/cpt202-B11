package com.example.finall.dao;

import org.apache.ibatis.annotations.Mapper;
import com.example.finall.pojo.Pet;

import java.util.List;

@Mapper
public interface PetMapper {
    int addPet(Pet pet);


    //删除一个Card int addBook(books books);
    int deleteByPetId(int pet_id);

    //更新一个Pet
    int updatePet(Pet pet);

    //查询一个Card
    Pet queryPetById(int petId);

    //Pet QueryPetByName(String pet_name);

    List<Pet> QueryPetByOwner(int owner_phone);

    List<Pet> viewPets();
}
