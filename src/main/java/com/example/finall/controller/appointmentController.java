package com.example.finall.controller;

import com.example.finall.pojo.*;
import com.example.finall.service.CustomerServiceImpl;
import com.example.finall.service.ManagerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class appointmentController {
    @Autowired
    ManagerServiceImpl managerService;
    @Autowired
    CustomerServiceImpl customerService;
    String groomerID="";
    String petID="";
    Date startTime;
    Date endTime;
    String notes;
    String serviceType;
    @RequestMapping("appointment")
    public String Appointment(){
        return "addAppointment";
    }
    @RequestMapping("addAppointments")
    public String addAPPointment(HttpSession session,
                                 Model model,
                                 @RequestParam("Groomer") String groomer_id,
                                 @RequestParam("Pet")String pet_id,
                                 @RequestParam("Time") String start_time,
                                 @RequestParam("notes")String notes,
                                 @RequestParam("Service")String service_type) throws ParseException {
        List<OrderInfo> orderAll=managerService.viewAllOrder();

        String[] timeArray = start_time.split("-");
        String startTime = timeArray[0];
        String endTime = timeArray[1];
        LocalDate tomorrows = LocalDate.now().plusDays(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String tomorrow=formatter.format(tomorrows);
        String startData=tomorrow+" "+startTime;
        String endData=tomorrow+" "+endTime;
        DateFormat formatters = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date start=formatters.parse(startData);
        Date end=formatters.parse(endData);
        System.out.println(start);
        System.out.println(end);
        for(OrderInfo orderInfo:orderAll){
            if(start==orderInfo.start_time && Integer.parseInt(groomer_id)==orderInfo.employee_id){
                return "errorAppointment";
            }
        }
        System.out.println(start+"fafafafafafafafafafafafa");
        this.groomerID=groomer_id;
        this.petID=pet_id;
        this.startTime=start;
        this.endTime=end;
        this.notes=notes;
        this.serviceType=service_type;
        //String odPrice=customerService.createOrder(Integer.parseInt(userId),groomer_id,pet_id,start,end,notes,service_type);
        //OrderInfo newOrder=new OrderInfo(Integer.parseInt(userId),groomer_id,pet_id,start,end,notes,service_type);

        //model.addAttribute("odPrice",odPrice);
        //if (odPrice.equals("1")) return "errorAppointment";
        String userId = (String) session.getAttribute("userId");
        String odPrice=customerService.createOrder(Integer.parseInt(userId),this.groomerID,this.petID,this.startTime,this.endTime,this.notes,this.serviceType);
        model.addAttribute("odPrice",odPrice);
        if (odPrice.equals("1")) return "errorAppointment";
        else{return "redictAppointments";}
    }
//    @RequestMapping("redictAppointment")
//    public String redictApp(HttpSession session,Model model){
//        String userId = (String) session.getAttribute("userId");
//        String odPrice=customerService.createOrder(Integer.parseInt(userId),this.groomerID,this.petID,this.startTime,this.endTime,this.notes,this.serviceType);
//        model.addAttribute("odPrice",odPrice);
//        if (odPrice.equals("1")) return "errorAppointment";
//        else{return "redictAppointments";}
//    }
    @RequestMapping("confirmApp")
    public String confirmApp(HttpSession session){
        String userId = (String) session.getAttribute("userId");
        customerService.addAppointment(Integer.parseInt(userId),this.groomerID,this.petID,this.startTime,this.endTime,this.notes,this.serviceType);
        return "Home";
    }
    @RequestMapping("appointments")
    public String example(Model model, HttpSession session) {
        List<Groomer> groomerList=managerService.viewGroomers();
        model.addAttribute("groomerList", groomerList);
        List<Pet> petList=customerService.viewPets();
        model.addAttribute("petList",petList);
        List<com.example.finall.pojo.Service> serviceList=customerService.viewAllService();
        model.addAttribute("serviceList",serviceList);
        return "addAppointment";
    }
    @RequestMapping("myAppointments")
    public String viewOrder(Model model, HttpSession session) {
        String userI = (String) session.getAttribute("userId");
        System.out.println(userI+"dadadadadadadada");
        List<OrderInfo> viewOrder=customerService.viewAllOrders(Integer.parseInt(userI));
        System.out.println(userI+"dadadadadadadadasssssss");
        List<OrderInfo> list2=new ArrayList<>();
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
            list2.add(newOrder);
        }
        model.addAttribute("list2",list2);
        System.out.println(list2);
        return "myAppointment";
    }
//    @RequestMapping("view")
//    public String view0rders(Model model){
//        model.addAttribute("views",managerService.viewAllOrder());
//        return "maintainAppointment";
//    }
    @RequestMapping("deleteOrder/{id}")
    public String deleteOrder(@PathVariable("id") String orderId,
                              HttpSession session){
        String user = (String) session.getAttribute("userId");
        customerService.deleteOrder(Integer.parseInt(user),orderId);
        return "Home";
    }
    @RequestMapping("/searchApp")
    public String search(Model model,@RequestParam("searchPhone") String phone){
        int phones= Integer.parseInt(phone);
        List<OrderInfo> searchApps=customerService.QueryOderByPhone(phones);
        model.addAttribute("searchApps",searchApps);
        return "maintainAppointment";
    }
    @RequestMapping("/searchAppointments")
    public String searchAppointments(Model model,@RequestParam("pet_ids") String pet_id){
        System.out.println("dadadada  hhjjj");
        int phones= Integer.parseInt(pet_id);
        List<OrderInfo> searchApps=customerService.QueryOrderByPet(pet_id);
        model.addAttribute("searchApps",searchApps);
        return "searchMyAppointment";
    }
    @RequestMapping("/completeOrder/{ids}")
    public String completeOrder(@PathVariable("ids") String order_id){
        int order_ids= Integer.parseInt(order_id);
        managerService.completeOrder(order_ids);
        return "Home";
    }
//    @RequestMapping("/completeEvaluationOrder/{ids}")
//    public String completeEvaluationOrder(@PathVariable("ids") String order_id){
//        int order_ids= Integer.parseInt(order_id);
//        managerService.completeOrder(order_ids);
//        return "managerPage";
//    }
    @RequestMapping("/addEvaluation")
    public String evaluation(){
        return "addEvaluation";
    }
    @RequestMapping("completeEvaluation")
    public String addEvaluation(@RequestParam("order_id") String order_id,
                                @RequestParam("shop_rating") String shop_rating,
                                @RequestParam("waiter_rating") String groomer_rating,
                                @RequestParam("service_rating") String service_rating,
                                @RequestParam("other")String otherComment,
                                HttpSession session){
        System.out.println("dashabidashabi      dadadavbbbbb");
        String userId = (String) session.getAttribute("userId");
        int phones = Integer.parseInt(userId);
        customerService.addEvaluations(order_id,phones,service_rating,shop_rating,groomer_rating,otherComment);
        customerService.completeEvaluationOrder(order_id);
        return "Home";
    }
    @RequestMapping("viewEvaluation")
    public String viewEva(Model model){
        List<Comments> commentsList = customerService.viewComments();
        model.addAttribute("commentsList",commentsList);
        return "Forum";
    }
    @RequestMapping("deleteEvaluation/{order}")
    public String deleteEvaluation(@PathVariable("order") String order_id){
        customerService.deleteEvaluation(Integer.parseInt(order_id));
        return "Home";
    }
}
