package com.example.finall.controller;

import com.example.finall.pojo.Card;
import com.example.finall.pojo.Groomer;
import com.example.finall.service.AccountServiceImpl;
import com.example.finall.service.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Base64;
import java.util.List;


@Controller
public class vipController {
    int userIds=0;
    boolean isVip=false;
    @Autowired
    AccountServiceImpl accountService;
    @Autowired
    CustomerServiceImpl customerService;
    @RequestMapping("isVip")
    public String viewVip(Model model,HttpSession session) {
        String userId = (String) session.getAttribute("userId");
        this.userIds = Integer.parseInt(userId);
        this.isVip=accountService.isVip(userIds);
        model.addAttribute("isVip", isVip);
        Card card=customerService.viewVip(userIds);
        model.addAttribute("card_id", userIds);
        model.addAttribute("balance", card.balance);
        return "VIP";
    }
    @PostMapping("recharge")
    public String rechargeVip(@RequestParam("phoneNumber") String phoneNumber,
                             @RequestParam("rechargeAmount") int rechargeAmount
                            ){
        //Card card =new Card(Integer.parseInt(phoneNumber),rechargeAmount);
        if (isVip){
            //Card card =new Card(Integer.parseInt(phoneNumber),rechargeAmount);
            customerService.chargeVip(rechargeAmount,Integer.parseInt(phoneNumber));
            System.out.println(phoneNumber+"shadiao");
            return "Home";
        }else{
            customerService.createVip(rechargeAmount,Integer.parseInt(phoneNumber));
            return "Home";
        }
    }
}
