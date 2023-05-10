package com.example.finall.controller;

import com.example.finall.pojo.Groomer;
import com.example.finall.pojo.Pet;
import com.example.finall.pojo.Product;
import com.example.finall.service.CustomerServiceImpl;
import com.example.finall.service.ManagerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Base64;
import java.util.List;

@Controller
public class productController {
    String productN="nmae";
    @Autowired
    ManagerServiceImpl managerService;
    @Autowired
    CustomerServiceImpl customerService;
    @RequestMapping("managerProduct")
    public String Product(){
        return "addProducts";
    }
    @RequestMapping("addProduct")
    public String addProduct(@RequestParam("ad_title")String name,
                             @RequestParam("upimage")MultipartFile image,
                             @RequestParam("ad_description")String decription,
                             @RequestParam("ad_price")String  price)throws SQLException, IOException {
        MultipartFile file = image;
        Product product =new Product(name,Integer.parseInt(price),decription,file.getBytes());
        managerService.addProduct(product);
        return "Home";
    }

    @RequestMapping("maintainProducts")
    public List<Product> viewProduct(){
        List<Product> productList =customerService.viewAllProduct();
        for(Product product:productList){
            if (product.getProductPicture() != null){
                product.setProductPicture2(Base64.getEncoder().encodeToString(product.getProductPicture()));
            }
        }
        return productList;
    }
    @RequestMapping("/deleteProduct/{name}")
    public String deleteProduct(@PathVariable("name") String name){
        managerService.deleteProduct(name);
        return "redirect:/maintainProducts";
    }
    @RequestMapping("updateProduct/{name}")
    public String update(@PathVariable("name") String name){
        this.productN=name;
        return"updateProducts";
    }
    @RequestMapping("updateProductss")
    public String updateProduct(
                             @RequestParam("upimage")MultipartFile image,
                             @RequestParam("ad_description")String decription,
                             @RequestParam("ad_price")String  price)throws SQLException, IOException {
        MultipartFile file = image;
        Product product =new Product(this.productN,Integer.parseInt(price),decription,file.getBytes());
        //managerService.updateProduct(product);
        return "Home";
    }
    @RequestMapping("/Product/{details}")
    public String product(@PathVariable("details") String proName, Model model){
        Product productDetails=customerService.viewProductDetails(proName);
        if (productDetails.getProductPicture() != null){
            productDetails.setProductPicture2(Base64.getEncoder().encodeToString(productDetails.getProductPicture()));
        }

        model.addAttribute("productDetails",productDetails);
        return "Products";
    }
}
