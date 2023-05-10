package com.example.finall.controller;


import com.example.finall.pojo.Groomer;
import com.example.finall.pojo.Pet;
import com.example.finall.pojo.Product;
import com.example.finall.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.finall.pojo.Advertisement;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Controller
public class advertisementController {
//    @Autowired
//    private AccountMapper accountService;
//    @Autowired
//    public ManagerService managerService;
//    @Autowired
//    public CustomerService customerService;
//    @Autowired
//    public GroomerService groomerService;

    String names="name";

    @Autowired
    ManagerServiceImpl managerServiceimpl;
    @RequestMapping("Advertisements")
    public String add(){
        return "addAdvertisement";
    }
    @RequestMapping("addAdvertisements")
    public String addAdvertisement(@RequestParam("ad_title")String ad_name,
                                   @RequestParam("ad_description") String ad_text,
                                   @RequestParam("upimage") MultipartFile image) throws IOException {
        MultipartFile file = image;
        Advertisement advertisement=new Advertisement(ad_name,ad_text,file.getBytes());
        managerServiceimpl.addAdvertise(advertisement);
        return "Home";
    }
    @RequestMapping("/deleteAdvertisement/{name}")
    public String deleteAdvertisements(@PathVariable("name") String name) {
        managerServiceimpl.deleteAdvertise(name);
        return "Home";
    }
    @RequestMapping("Advertise")
    public String viewAdvertisement(Model model){
        List<Advertisement> advertisementList = managerServiceimpl.ViewAdvertisement();
        System.out.println(advertisementList);
        for(Advertisement advertisement:advertisementList){
            if (advertisement.getLongblob_() != null){
                advertisement.setLongblob(Base64.getEncoder().encodeToString(advertisement.getLongblob_()));
            }
        }
        model.addAttribute("advertisementList",advertisementList);
        return "Advertisement";
    }
//    @RequestMapping("adver")
//    public String viewadver(Model model){
//        List<Advertisement> advertisementList = managerServiceimpl.ViewAdvertisement();
//        System.out.println(advertisementList);
//        for(Advertisement advertisement:advertisementList){
//            if (advertisement.getLongblob_() != null){
//                advertisement.setLongblob(Base64.getEncoder().encodeToString(advertisement.getLongblob_()));
//            }
//        }
//        model.addAttribute("advertisementList",advertisementList);
//        return "Advertisement";
//    }
//    @RequestMapping("updateAdvertisement/{name}")
//    public String chooseAd(@PathVariable("name") String name){
//        this.names=name;
//        return "home";
//    }
//    @RequestMapping("Home")
//    public List<Advertisement> viewHomeAd(){
//        List<Advertisement> adList =managerServiceimpl.ViewAdvertisement();
//        for(Advertisement advertisement:adList){
//            if (advertisement.getLongblob_() != null){
//                advertisement.setLongblob(Base64.getEncoder().encodeToString(advertisement.getLongblob_()));
//            }
//        }
//        return adList;
//    }

}
