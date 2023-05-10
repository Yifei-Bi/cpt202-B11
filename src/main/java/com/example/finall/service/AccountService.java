package com.example.finall.service;

import com.example.finall.pojo.Customer;
import com.example.finall.pojo.Manager;


public interface AccountService {
    Customer save(String user_name, String password, int phone);

    Customer isCustomer(int phone,String password);

    //int save(Manager manager);

    Manager isManager(int phone, String password);

    boolean isaccount(int phone);
    //int save(Groomer groomer);
    //int deleteGroomer(int exployee_id);
    boolean isVip(int phone);

}
