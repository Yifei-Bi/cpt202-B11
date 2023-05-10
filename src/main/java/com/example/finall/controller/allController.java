package com.example.finall.controller;

import com.example.finall.pojo.Customer;
import com.example.finall.service.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class allController {
    @Autowired
    CustomerServiceImpl customerService;
    @RequestMapping("home")
    public String home(HttpSession session){
        String se="0";
        session.setAttribute("userId", se);

        String userId = (String) session.getAttribute("userId");
        int useIds= Integer.parseInt(userId);
        System.out.println(useIds);
        return "Home";
    }
    @RequestMapping("ho")
    public String homes(){
        return "Home";
    }
    @RequestMapping("cus")
    public String customer(HttpSession session,Model model){
        String userId = (String) session.getAttribute("userId");
        int useIds= Integer.parseInt(userId);
        System.out.println(
                useIds+"dadadadadadada"
        );
        if (useIds==0||useIds==20001){return "Page";}
        else {
            Customer viewInfor=customerService.viewInformation(useIds);
            model.addAttribute("viewInfor",viewInfor);
            return "Customer";}
    }
    @RequestMapping("man")
    public String man(HttpSession session){
        String userId = (String) session.getAttribute("userId");
        int useIds= Integer.parseInt(userId);
        if (useIds==20001){return "managerPage";}
        else {return "Page";}
    }
    @RequestMapping("for")
    public String fors(HttpSession session){
        String userId = (String) session.getAttribute("userId");
        int useIds= Integer.parseInt(userId);
        if (useIds==0){return "Page";}
        else {return "Forum";}
    }
//    @RequestMapping("ser")
//    public String ser(HttpSession session){
//        String userId = (String) session.getAttribute("userId");
//        int useIds= Integer.parseInt(userId);
//        if (useIds==0){return "Page";}
//        else {return "Service";}
//    }
    @RequestMapping("changeInformation")
    public String changeIn(){
        return "modifyPersonalInformation";
    }
    @RequestMapping("modifyInformation")
    public String confirm(HttpSession session,@RequestParam("newUsername") String username,Model model){
        String userId = (String) session.getAttribute("userId");
        int useIds= Integer.parseInt(userId);
        customerService.updateCustomer(useIds,username);
        Customer viewInfor=customerService.viewInformation(useIds);
        model.addAttribute("viewInfor",viewInfor);
        return "Home";
    }
    @RequestMapping("pass")
    public String pass(){
        return "modifyPassword";
    }
    @RequestMapping("modifyPass")
    public String modifypass(@RequestParam("password") String password,
                             HttpSession session){
        String userId = (String) session.getAttribute("userId");
        int useIds= Integer.parseInt(userId);
        customerService.updatePassword(useIds,password);
        return "Home";
    }
    @RequestMapping("op")
    public String op(HttpSession session, Model model){
        String userId = (String) session.getAttribute("userId");
        int useIds= Integer.parseInt(userId);
        Customer viewInfor=customerService.viewInformation(useIds);
        model.addAttribute("viewInfor",viewInfor);
        return "Customer";
    }
    @RequestMapping("job")
    public String job(){
        return "Recruitment";
    }
    @RequestMapping("jobs")
    public String jobs(){
        return "Home";
    }

}
