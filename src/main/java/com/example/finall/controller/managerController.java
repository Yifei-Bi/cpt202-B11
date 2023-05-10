package com.example.finall.controller;

import com.example.finall.pojo.Card;
import com.example.finall.pojo.OrderInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.finall.pojo.Customer;
import com.example.finall.service.ManagerServiceImpl;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Controller
public class managerController {
    @Autowired
    ManagerServiceImpl customers;
    @RequestMapping("manageCustomer")
    public List<Customer> getallCustomer(){
        List<Customer> customerList=customers.getAllCustomer();
        return customerList;
    }

//    public String getCustomer(Model model, HttpSession session) {
//        String userId = (String) session.getAttribute("userId");
//        int userIds = Integer.parseInt(userId);
//        Customer customerList=customers.getCustomer(userIds);
//        model.addAttribute("userName",customerList.user_name);
//        model.addAttribute("userIds",userIds);
//
//        return "manageCustomer";
//    }deleteCustomer
    @RequestMapping("deleteCustomer/{phone}")
    public String deleteCustomer(@PathVariable("phone") String phone){
        System.out.println(phone+"      dadadadafafa");
        //没进去
        customers.deleteCustomer(Integer.parseInt(phone));
        return "redirect:/manageCustomer";
    }

    @RequestMapping("mang")
    public String mangs(){
        return"Groomer";
    }
    @RequestMapping("custo")
    public String custos(){
        return "manageCustomer";
    }
    @RequestMapping("pro")
    public String pros(){
        return "maintainProducts";
    }
    @RequestMapping("managerAppointments")
    public String mangA(Model model, HttpSession session) {
        String userId = (String) session.getAttribute("userId");
        System.out.println(userId+"dadadadadadadada");
        List<OrderInfo> viewOrder=customers.viewAllOrder();
        System.out.println(userId+"dadadadadadadadasssssss");
        List<OrderInfo> list3=new ArrayList<>();
        //ArrayList<String> list3=new ArrayList<>();
        for(OrderInfo orderInfo:viewOrder){
//           list2.add(orderInfo.start_time.toString());
//            list3.add(orderInfo.end_time.toString());
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            System.out.println(orderInfo.start_time.toString());
            System.out.println(orderInfo.end_time.toString());
            String start_time2=formatter.format(orderInfo.start_time);
            String end_time2=formatter.format(orderInfo.end_time);
            OrderInfo newOrder=new OrderInfo(orderInfo.phone,orderInfo.pet_id,orderInfo.order_id,orderInfo.status,orderInfo.employee_id,orderInfo.service_type,orderInfo.notes,start_time2,end_time2);
            list3.add(newOrder);
        }
        model.addAttribute("list3",list3);
        return "managerAppointment";
    }
}
