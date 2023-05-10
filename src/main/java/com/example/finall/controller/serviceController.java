package com.example.finall.controller;

import com.example.finall.pojo.Product;
import com.example.finall.pojo.Service;
import com.example.finall.service.CustomerServiceImpl;
import com.example.finall.service.ManagerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Base64;
import java.util.List;

@Controller
public class serviceController {
    String serviceName="abc";
    @Autowired
    ManagerServiceImpl managerService;
    @Autowired
    CustomerServiceImpl customerService;
    @RequestMapping("Services")
    public String service(){
        return "addService";
    }
    @RequestMapping("addServices")
    public String addService(@RequestParam("Type") String name,
                             @RequestParam("normalPrice") String decription,
                             @RequestParam("vipPrice") String price) {
        com.example.finall.pojo.Service service=new com.example.finall.pojo.Service(name,decription,Integer.parseInt(price));
        managerService.addService(service);
        return "Home";
    }
    @RequestMapping("ser")
    public String viewService(Model model, HttpSession session){
        List<com.example.finall.pojo.Service> serviceList =customerService.viewAllService();
        for(com.example.finall.pojo.Service ss:serviceList ){
            ss.setVipPrice((int) (ss.price*0.7));
        }
        model.addAttribute("serviceList",serviceList);
        String userId = (String) session.getAttribute("userId");
        int useIds= Integer.parseInt(userId);
        if (useIds==0){return "Page";}
        else {return "Service";}
    }
    @RequestMapping("deleteService/{name}")
    public String deleteService(@PathVariable("name") String name){
        managerService.deleteService(name);
        return "Home";
    }
    @RequestMapping("updateservice/{name}")
    public String updateService(@PathVariable("name") String name){
        this.serviceName=name;
        return "updateService";
    }
    @RequestMapping("updateServices")
    public String updateServices(@RequestParam("content") String decription,
                                @RequestParam("normalPrice") String price){
        com.example.finall.pojo.Service service=new com.example.finall.pojo.Service(serviceName,decription,Integer.parseInt(price));
        managerService.updateService(service);
        return "redirect:/Service";
    }

}
