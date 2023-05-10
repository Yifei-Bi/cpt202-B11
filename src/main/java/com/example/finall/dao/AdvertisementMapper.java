package com.example.finall.dao;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.example.finall.pojo.Advertisement;

import java.util.List;

@Mapper
public interface AdvertisementMapper {
        int addAdvertise(Advertisement advertisement);

        String upateAdvertise(Advertisement advertisement);
        int deleteAdvertiseByName(String ad_Name);
        //String deleteAdvertise(String ad_name);

        //List<Advertisement> getList(String content, String ad_name,String filepath);
        Advertisement getList(String ad_name);

        List<Advertisement> viewAdvertisement();



}

