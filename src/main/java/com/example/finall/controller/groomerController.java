package com.example.finall.controller;


import com.example.finall.service.GroomerService;
import com.example.finall.service.ManagerService;
import org.apache.ibatis.javassist.bytecode.FieldInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.example.finall.pojo.Groomer;
import com.example.finall.service.ManagerServiceImpl;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

@Controller
public class groomerController {
    int id=0;
    ManagerServiceImpl groomer = new ManagerServiceImpl();
    @Autowired
    ManagerService managerService;
    @Autowired
    GroomerService groomerService;
    @RequestMapping("addGroomer")
    public String Groomer(){
        return "addGroomer";
    }
    @PostMapping("addGroomers")
    public String addGroomer(@RequestParam("name") String groomer_name,
                            @RequestParam("rank")String rank,
                            @RequestParam("ad_description")String exp,
                            @RequestParam("ID")String employee_id,
                            @RequestParam("upimage") MultipartFile image) throws SQLException, IOException {

        MultipartFile file = image; // 接收到的文件
//       Blob blob = convertToBlob(file); // 将文件转换为Blob类型
        Groomer groomers = new Groomer(groomer_name,Integer.parseInt(rank),exp,Integer.parseInt(employee_id),file.getBytes(),1);
        managerService.saveGroomers(groomers);
        System.out.println(groomer_name+rank+exp);
        return "Home";
   }
    public Blob convertToBlob(MultipartFile file) throws IOException, SQLException {
        byte[] bytes = file.getBytes();
        return new SerialBlob(bytes);
    }



    @RequestMapping("Groomer")
    public List<Groomer> viewGroomer(){
        List<Groomer> groomerList = managerService.viewGroomers();
//        System.out.println(groomerList.size());
//        ArrayList<Groomer> newList= new ArrayList<>();
//        myutil myutil=new myutil();
        for(Groomer groomer:groomerList){
//            Groomer groomer1= new Groomer(groomer.getGroomerName(), groomer.getRank(), groomer.getExperience(),groomer.getEmployeeId(),myutil.blobToBase64String(groomer.getGroomerPicture()),groomer.getIsFree());
//            System.out.println("name"+groomer1.getGroomerName());
//            System.out.println("rank"+groomer1.getRank());
//            System.out.println("picture"+groomer1.getGroomerPicture());
//            System.out.println("exp"+groomer1.getExperience());
            //System.out.println("name"+groomer1.getGroomerName());
            groomer.setGroomerPicture2(Base64.getEncoder().encodeToString(groomer.getGroomerPicture()));

        }
//        System.out.println(newList.size());
        return groomerList;
    }

    @RequestMapping("/delete/{id}")
    public String deleteData(@PathVariable("id") String id) {
        managerService.deleteGroomer(Integer.parseInt(id));
        return "redirect:/Groomer";
    }
    @RequestMapping("updateGroomers/{id}")
    public String update(@PathVariable("id") String employee_id){
        this.id = Integer.parseInt(employee_id);
        System.out.println(id);
        return "updateGroomer";
    }
    @RequestMapping("/updateGroomerss")
    public String update(@RequestParam("name") String groomer_name,
                         @RequestParam("rank")String rank,
                         @RequestParam("ad_description")String exp,
                         @RequestParam("upimage") MultipartFile image) throws SQLException, IOException {

        int employee_id=this.id;
        MultipartFile file = image; // 接收到的文件
//       Blob blob = convertToBlob(file); // 将文件转换为Blob类型
        Groomer groomers = new Groomer(groomer_name,Integer.parseInt(rank),exp,employee_id,file.getBytes(),1);
        managerService.updateGroomer(groomers);
        System.out.println(groomer_name+rank+exp);
        return "Home";

    }

    //@RequestMapping("update")
    /*
    public String updateGroomer(String groomer_name,int rank,String exp,String employee_id){
        Groomer groomers = new Groomer(groomer_name,rank,exp,employee_id);
        groomer.updateGroomer(groomers);
        return "home";
    }

     */

}
