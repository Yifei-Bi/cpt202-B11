package com.example.finall.dao;

import org.apache.ibatis.annotations.Mapper;
import com.example.finall.pojo.Manager;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ManagerMapper {
    int addManager(Manager manager);


    //删除一个Card int addBook(books books);
    int deleteManagerByName(String manager_name);
    int deleteManager(int phone);

    //
    int updateManager(Manager manager);

    //
    Manager queryManagerByPhone(@Param("phone") int phone);

    List<Manager> getAllManager ();
}
