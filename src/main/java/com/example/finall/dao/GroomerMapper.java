package com.example.finall.dao;

import org.apache.ibatis.annotations.Mapper;
import com.example.finall.pojo.Groomer;

import java.util.List;

@Mapper
public interface GroomerMapper {
    int addGroomer(Groomer groomer);


    //通过groomer_name删除groomer;
    int deleteByGroomerName(String groomer_name);
    //通过groomer_id删除groomer;
    int deleteByGroomerId(int employee_id);
//    int deleteByGroomer();

    //更新groomer
    int updateGroomer(Groomer groomer);

    //查询groomer
    Groomer queryGroomerByName(String groomer_name);

    Groomer queryGroomerById(int employee_id);

    List<Groomer> queryGroomerByRank(int rank);

    List<Groomer> listAllGroomer();
}
