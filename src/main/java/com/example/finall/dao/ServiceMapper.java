package com.example.finall.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.example.finall.pojo.Service;

import java.util.List;

@Mapper
public interface ServiceMapper {
    //创建一个Card int addBook(books books);
    int addService(Service service);


    //删除一个Card int addBook(books books);
    int deleteServiceByName(String service_type);


    //更新一个Card
    int updateService(Service service);

    //查询一个Card
    Service QueryServiceByName(String service_type);
    List<Service> QueryService();
}
