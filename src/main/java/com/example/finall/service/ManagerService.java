package com.example.finall.service;

import com.example.finall.pojo.*;

import java.util.List;

public interface ManagerService {
    //管理所有的groomers
    String saveGroomers(Groomer groomer);

//    int deleteGroomer();
    String deleteGroomer(int employee_id);
    String updateGroomer(Groomer groomer);

//    String addAdvertise(Advertisement advertisement);
//    String updateAdvertise(Advertisement advertisement);

    String deleteAdvertise(String ad_name);
    //查看所有预约
    List<OrderInfo> viewAllOrder();
    List<Groomer> viewGroomers();

    //搜索预约
    List<OrderInfo> QueryOrderByPet(int pet_id);
    OrderInfo QueryOrderById(String order_id);

    List<OrderInfo> QueryOderByPhone(int phone);
    String deleteOrderByPhone(int phone);

    String deleteOrderByOrder_id(String order_id);






    Groomer getGroomer(int employee_id);
    //List<Groomer> getAllGroomer(String groomer_name);
    List<Groomer> getGroomers(int rank);
    //List<Groomer> getAllGroomer();

//    int save(Manager manager);
//    int deleteManager();
    //int updateManager(Manager manager);
    //Manager getManager(int manager_id);
    //List<Manager> getAllManager(String manager_name);

//    int save(Customer customer);
    String deleteCustomer(int phone);
    //int updateCustomer(Customer customer);
    Customer getCustomer(int phone);
    //List<Manager> getAllCustomer(String user_name);
    List<Customer> getAllCustomer();

    //List<OrderInfo> orderinfo();
    //List<OrderInfo> orderinfo(int exployee_id);
    //List<OrderInfo> orderinfo(String phone);

    String addService(Service service);

    public String addProduct(Product product);

    public String updateService(com.example.finall.pojo.Service service);

    public String deleteService(String name);

    public  String addAdvertise(Advertisement advertisement);

    public List<Advertisement> ViewAdvertisement();

    public void completeOrder(int order_id);


}
