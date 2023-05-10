package com.example.finall.service;

import com.example.finall.pojo.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface CustomerService {
    String addPet(Pet pet);

    String updatePet(Pet pet);
    List<Pet> getPet(int owner_phone);

    String deletePet(int pet_id);

    String updatePassword(int phone,String new_password);

    List<OrderInfo> orderinfo(int phone);

    //账户
    int updateCustomer(Customer customer);

    String deleteCustomer(int phone);



    Groomer viewGroomers(int employee_id);

    List<Service> viewAllService();

    //查看服务详情
    String viewServiceDetails(String type);
    //客户查看所有预约
    List<OrderInfo> viewAllOrders(int phone);
    //客户搜索预约
    List<OrderInfo> QueryOrderByPet(String pet_id);
    OrderInfo QueryOrderById(Customer customer,String order_id);

    List<OrderInfo> QueryOderByPhone(int phone);
    //客户查看预约详情
    OrderInfo viewOrderDetails(String order_id);

    String deleteOrder(int phone,String order_id);

    //办理会员
    String createVip(int money,int phone);

    String chargeVip(int money,int phone);

    Card viewVip(int phone);

    //查看商品 by yin
    List<Product> viewAllProduct();

    //查看商品详情 by yin
    Product viewProductDetails(String name);

    //购买商品 by yin
    //String buyProduct(String name, int money, int phone);
    String buyProduct(String name, int phone);



    String commentShopAndGroomers(Comments comments);

    //论坛发表评论
    String postComments(Comments comments);

    int calculateExpense(Pet pet, Groomer groomer, String service_type);

    String createOrder(int phone, String groomer_id, String pet_id, Date start_time, Date end_time, String notes, String service_type);
    OrderInfo createorder(int phone, String groomer_id, String pet_id, String order_id,Date start_time, Date end_time, String status,String notes,String service_type);
    //String OrderByPhone();
    //String updateOrder(final String order_id, final String status, int phone, String groomer_id, String pet_id, Date start_time, Date end_time, String notes, String service_type);

    Map<Groomer, List<String>> viewAvilabletime();

    public List<Pet> viewPets();

    public Customer viewInformation(int phone);

    public void updateCustomer(int phone,String username);

    public Product ViewProductDetails(String name);

    public void addEvaluations(String order_id,int phones,String service_rating,String shop_rating,String groomer_rating,String otherComment);

    public void completeEvaluationOrder(String order_id);

    public List<Comments> viewComments();

    public void addAppointment(int phone,String groomer_id, String pet_id,Date start_time, Date end_time,String note, String service_type);

    public void deleteEvaluation(int order_id);

}
