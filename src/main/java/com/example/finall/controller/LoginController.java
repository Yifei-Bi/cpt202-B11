package com.example.finall.controller;

import com.example.finall.pojo.Customer;
import com.example.finall.pojo.Manager;
import com.example.finall.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    int checkLogin=0;
    @Autowired
    private AccountService accountService;

    @RequestMapping("Pages")
    public String login(HttpSession session) {
        String userId = (String) session.getAttribute("userId");
        int userIds = Integer.parseInt(userId);
        if(userIds==0)   return "Page";
        else{return "Home";}

    }
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String Login(HttpSession session,
                        @RequestParam("username")String phone,
                        @RequestParam("password")String password) {
        int num = 0;
        num = Integer.parseInt(phone);
        Customer customer = accountService.isCustomer(num, password);
        Manager manager = accountService.isManager(num, password);
        if (customer == null && manager == null) {
            return "Register";
        } else if (manager != null) {
            checkLogin=1;
            session.setAttribute("userId", phone);

            return "Home";
        } else {
            checkLogin=0;
            session.setAttribute("userId", phone);
            System.out.println(session.getAttribute("userId"));
            return "Home";
        }
    }
    @RequestMapping("logout")
    public String logout(HttpSession session){
        String s="0";
        session.setAttribute("userId",s);
        return "Home";
    }
    @RequestMapping("Register")
    public String Register(){
        return "Register";
    }
    @RequestMapping("/register")
    public String Registers(@RequestParam("username")String usename,
                            @RequestParam("phoneNumber")String phoneNumber,
                            @RequestParam("password")String password,
                            @RequestParam("newpassword")String surepassword){
        System.out.println(usename+password+phoneNumber+surepassword);
        boolean isAccountExist = accountService.isaccount(Integer.parseInt(phoneNumber));
        if (isAccountExist == false) {
            accountService.save(usename, password, Integer.parseInt(phoneNumber));
            return "Page";
        } else {
            return "Page";
        }
    }


}
