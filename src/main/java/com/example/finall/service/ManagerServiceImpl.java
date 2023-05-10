package com.example.finall.service;

import com.example.finall.dao.*;
import com.example.finall.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service("managerService")

public class ManagerServiceImpl implements ManagerService{
    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private ManagerMapper managerMapper;
    @Autowired
    private ServiceMapper serviceMapper;
    @Autowired
    private PetMapper petMapper;
    @Autowired
    private GroomerMapper groomerMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private AdvertisementMapper advertisementMapper;
    @Autowired
    private ProductMapper productMapper;


//    public ManagerServiceImpl(CustomerMapper customerMapper, ManagerMapper managerMapper, GroomerMapper groomerMapper) {
//        this.customerMapper = customerMapper;
//        this.managerMapper = managerMapper;
//        this.groomerMapper = groomerMapper;
//    }
    public Customer getCustomer(int phone){
        Customer customer=customerMapper.queryCustomerByPhone(phone);
        return customer;
    }


    public List<Customer> getAllCustomer(){
        List<Customer> customers=customerMapper.getAllCustomers();
        return customers;
    }
    public String deleteCustomer(int phone){
        Customer customer=customerMapper.queryCustomerByPhone(phone);
        System.out.println("dadadada111111111111");
        customerMapper.deleteByPhone(phone);
        System.out.println("dadadada111111111111");
        List<Pet> pets=petMapper.QueryPetByOwner(phone);
        List<OrderInfo> orderInfos=orderMapper.QueryOrderByPhone(phone);
        for(Pet pet:pets){
            petMapper.deleteByPetId(pet.pet_id);
        }
        for(OrderInfo orderInfo:orderInfos){
            orderMapper.deleteOrderById(String.valueOf(orderInfo.order_id));
        }
        return phone +"has been deleted";
    }

    public String saveGroomers(Groomer groomer){
        Groomer g=groomerMapper.queryGroomerById(groomer.getEmployeeId());
        if(g==null){
            groomerMapper.addGroomer(groomer);
            return "groomer is added sucessfully";
        }

        return "groomer has exists";


    }

    public String updateGroomer(Groomer groomer){
        Groomer g=groomerMapper.queryGroomerById(groomer.getEmployeeId());
        if(g!=null){
            groomerMapper.updateGroomer(groomer);
            return "groomer information has been updated";
        }
        return "groomer information has not been updated";
    }

    public String deleteGroomer(int employee_id){
        groomerMapper.deleteByGroomerId(employee_id);
        return "groomer has been deleted";
    }

    public String addService(com.example.finall.pojo.Service service){
        serviceMapper.addService(service);
        return "new service is added";
    };

    public String addProduct(Product product){
        //Product newProduct = productMapper.QueryProductByName(product.product_name);
        productMapper.addProduct(product);
        return "product add sucessfully";
//        if(newProduct!=null){
//            productMapper.addProduct(product);
//            return "product add sucessfully";
//        }
        //return "product has exists";
    }
    public String deleteProduct(String product_name){
        productMapper.deleteProductByname(product_name);
        return "delete successfully";
    }



    public List<OrderInfo> viewAllOrder(){
        List list= orderMapper.QueryOrders();
        return list;
    }

    public List<OrderInfo> QueryOrderByPet(int pet_id){
        List<OrderInfo> list=orderMapper.QueryOrderByPet(pet_id);
        return list;
    }

    public OrderInfo QueryOrderById(String order_id){
        OrderInfo orderInfo=orderMapper.QueryOrderById(order_id);
        return orderInfo;
    }

    public List<OrderInfo> QueryOderByPhone(int phone){
        List<OrderInfo> list=orderMapper.QueryOrderByPhone(phone);
        return list;
    }

    public String deleteOrderByPhone(int phone){
        orderMapper.QueryOrderByPhone(phone);
        return "successfully delete by phone";
    }

    public String deleteOrderByOrder_id(String order_id){
        orderMapper.deleteOrderById(order_id);
        return "successfully delete order_id";
    }


//    public String addAdvertise(Advertisement advertisement){
//        advertisementMapper.addAdvertise(advertisement);
//        return "advertisement has been added";
//    }
//
//    public String updateAdvertise(Advertisement advertisement){
//        advertisementMapper.upateAdvertise(advertisement);
//        return "advertisement has been updated";
//    }

    public String deleteAdvertise(String ad_name){
        advertisementMapper.deleteAdvertiseByName(ad_name);
        return "Advertisement has been deleted";
    }

//    public List<OrderInfo> orderinfo(int phone){
//        List<OrderInfo> list=orderMapper.QueryOrderByPhone(phone);
//    }

    public Groomer getGroomer(int employee_id){
        Groomer groomer=groomerMapper.queryGroomerById(employee_id);
        return groomer;
    }

    public List<Groomer> getGroomers(int rank){
        List<Groomer> list=groomerMapper.queryGroomerByRank(rank);
        return list;
    }
    public List<Groomer> viewGroomers(){
        List<Groomer> list=groomerMapper.listAllGroomer();
        return list;
    }
    public String addAdvertise(Advertisement advertisement){
       Advertisement rs= advertisementMapper.getList(advertisement.getAd_name());
        if(rs!=null)
            return "Content can not repeat";
        else {
            advertisementMapper.addAdvertise(advertisement);
            return "advertisement add successfully";
        }
    }
//    public String saveGroomers(Groomer groomer){
//        Groomer g=groomerMapper.queryGroomerById(groomer.getEmployeeId());
//        if(g==null){
//            groomerMapper.addGroomer(groomer);
//            return "groomer is added sucessfully";
//        }
//
//        return "groomer has exists";
//
//
//    }

    public String updateAdvertise(Advertisement advertisement){
        advertisementMapper.upateAdvertise(advertisement);
        return "advertisement has been updated";
    }

    public List<Advertisement> ViewAdvertisement(){
       List<Advertisement> list= advertisementMapper.viewAdvertisement();
       return list;
    }
    public List<com.example.finall.pojo.Service> RecommendedPackage(String[] typenames){
        List<com.example.finall.pojo.Service> resultList = null;
        for(int i = 0; i<= Arrays.stream(typenames).count(); i++){
            //List<pojo.Service>  tmpList=serviceMapper.getlist(typenames[i]);
            //resultList.addAll(tmpList);
        }
        return resultList;

    }

    public String updateService(com.example.finall.pojo.Service service){
        //com.example.finall.pojo.Service ss=serviceMapper.QueryServiceByName(service.type_name);
        serviceMapper.updateService(service);
        return "update service successfull";

//        if(ss==null){
//            serviceMapper.updateService(service);
//            return "update service successfull";
//        }else{
//            return "update service fail";
//        }

    }
    public String deleteService(String name){
        serviceMapper.deleteServiceByName(name);
        return "delete service sucessfully";
    }

    public void completeOrder(int order_id){
        orderMapper.updateStatus(order_id,"completed");
    }



}
