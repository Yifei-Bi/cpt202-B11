package com.example.finall.service;

import com.example.finall.dao.CardMapper;
import com.example.finall.dao.CustomerMapper;
import com.example.finall.dao.GroomerMapper;
import com.example.finall.dao.ManagerMapper;
import com.example.finall.pojo.Card;
import com.example.finall.pojo.Customer;
import com.example.finall.pojo.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//@Service("accountService")
@Service
public class AccountServiceImpl implements AccountService{
    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private ManagerMapper managerMapper;
    @Autowired
    private GroomerMapper groomerMapper;
    @Autowired
    private CardMapper cardMapper;

    public AccountServiceImpl(CustomerMapper customerMapper, ManagerMapper managerMapper, GroomerMapper groomerMapper) {
        this.customerMapper = customerMapper;
        this.managerMapper = managerMapper;
        this.groomerMapper = groomerMapper;
    }


    //    public static AccountServiceImpl accountService;
//    @PostConstruct
//    public void init(){
//        accountService = this;
//        accountService.customerMapper = this.customerMapper;
//        accountService.managerMapper=this.managerMapper;
//    }
    @Override
    public Customer save(String user_name, String password, int phone) {

        Customer customer=new Customer(user_name,password, phone);
        customerMapper.addCustomer(customer);
        return customer;
    }

//    @Override
//    public int save(Manager manager) {
//        return managerMapper.addManager(manager);
//    }
    @Override
    public Customer isCustomer(int phone,String password){
        Customer customer = customerMapper.queryCustomerByPhone(phone);
        //查询数据库账户信息，并判断是否是customer还是manager
        if(customer != null && password.equals(customer.password)){
//            customer=customerMapper.queryCustomerByPhone(phone);
            return customer;
        }

        return null;
    }

//    @Override
//    public int deleteManager(String phone) {
//        return managerMapper.deleteManager(phone);
//    }
    public Manager isManager(int phone, String password){
        Manager manager = managerMapper.queryManagerByPhone(phone);
        if(manager != null && password.equals(manager.password)){
//            manager=managerMapper.queryManagerByPhone(phone);
            return  manager;
        }
        return null;
    }

    public boolean isaccount(int phone){
        if(customerMapper.queryCustomerByPhone(phone)==null&&managerMapper.queryManagerByPhone(phone)==null){
            return false;
        }else{
            return true;
        }

    }


    public boolean isVip(int phone){
        List<Card> list=cardMapper.IsVip(phone);
        if(list.size()==1){
            return true;
        }else{
            return false;
        }
    }
//    public int save(Groomer groomer) {
//        return groomerMapper.addGroomer(groomer);
//    }


//    public int deleteGroomer(int exployee_id) {
//        return groomerMapper.addGroomer(groomer);
//    }
}
