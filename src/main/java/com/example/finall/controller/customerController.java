package com.example.finall.controller;

import com.example.finall.pojo.Groomer;
import com.example.finall.pojo.Pet;
import com.example.finall.pojo.Service;
import com.example.finall.service.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

@Controller
public class customerController {
    int petId=0;
    @Autowired
    private CustomerServiceImpl customerService;
    //CustomerServiceImpl managerCustomer=new CustomerServiceImpl();
    @RequestMapping("addPets")
    public String Pet(){
        return "addPets";
    }
    @RequestMapping("addManagerPet")
    public String addPet(@RequestParam("ID") String pet_id,
                         @RequestParam("name")String pet_name,
                         @RequestParam("size")String size,
                         @RequestParam("type")String type,
                         @RequestParam("phone")String phone){
        Pet pet=new Pet(type,pet_name,
                Integer.parseInt(pet_id),
                Integer.parseInt(size),
                Integer.parseInt(phone));
        customerService.addPet(pet);
        return "Home";
    }
    @RequestMapping("managePets")
    public List<Pet> viewPets(){
        List<Pet> petList =customerService.viewPets();
        return petList;
    }
    @RequestMapping("updatePet/{id}")
    public String updatePets(@PathVariable("id") String pet_id){
        this.petId= Integer.parseInt(pet_id);
        return "updatePets";
    }


    @RequestMapping("/updatePetss")
    public String updatePet(@RequestParam("name") String pet_name,
                            @RequestParam("size") String size,
                            @RequestParam("type") String type,
                            @RequestParam("phone") String phone){
        Pet pet=new Pet(type,pet_name,
                this.petId,
                Integer.parseInt(size),
                Integer.parseInt(phone));
        customerService.updatePet(pet);
        return "Home";
    }
    @RequestMapping("deletePet/{id}")
    public String deletePet(@PathVariable("id") String id){
        customerService.deletePet(Integer.parseInt(id));
        return "redirect:/managePets";
    }
    @DeleteMapping("deletecustom/{id}")
    public String deleteCustomer(@PathVariable("id") String phone){
        customerService.deleteCustomer(Integer.parseInt(phone));
        return "Home";
    }
    @RequestMapping("/updatePassword")
    public String updatePassword(int phone,String new_password){
        customerService.updatePassword(phone, new_password);
        return "Home";
    }


}
