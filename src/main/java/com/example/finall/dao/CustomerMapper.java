package com.example.finall.dao;

import com.example.finall.pojo.Card;
import com.example.finall.pojo.Customer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CustomerMapper {
    //创建一个Customer;
    int addCustomer(Customer customer);


    //删除一个Card int addBook(books books);

    //int deleteCustomer();
    void deleteByPhone(int phone);

    //更新一个
    int UpdateCustomer(Customer customer);

    List<Customer> getAllCustomers();

    int UpdatePassword(int phone, String password); // 动态修改!!!!!!!!!!!!

    //查询
    //Customer QueryCustomer(String user_name);
    Customer queryCustomerByPhone(@Param("phone") int phone);

    //查询
    Customer ViewInformation(int phone);

    void UpdateCustomerInfo(int phone,String newUsername);



}
