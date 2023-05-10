package com.example.finall.service;

import com.example.finall.dao.GroomerMapper;
import com.example.finall.pojo.Groomer;
import com.example.finall.pojo.OrderInfo;
import org.springframework.stereotype.Service;

import java.util.List;

//@Service("groomerService")
@Service
public class GroomerServiceImpl implements GroomerService{
    private GroomerMapper groomerMapper;


    @Override
    public Groomer getGroomer(int employee_id) {
        return groomerMapper.queryGroomerById(employee_id);
    }

    @Override
    public List<OrderInfo> orderinfo(int employee_id) {
        return null;
    }
}
