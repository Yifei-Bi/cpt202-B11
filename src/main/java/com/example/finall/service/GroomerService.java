package com.example.finall.service;

import com.example.finall.pojo.Groomer;
import com.example.finall.pojo.OrderInfo;

import java.util.List;

public interface GroomerService {
    List<OrderInfo> orderinfo(int employee_id);
    Groomer getGroomer(int employee_id);
}
