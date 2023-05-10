package com.example.finall.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderInfo {
    public int phone;

    public int pet_id;
    public int order_id;
    public Date start_time;
    public Date end_time;
    public String status;


    public int employee_id; // 后添加!!!!!!!!!!!!!!!!



    public String service_type;

    public String getService_type() {
        return service_type;
    }

    public void setService_type(String service_type) {
        this.service_type = service_type;
    }

    public String notes;

    public String start_time2;
     public String end_time2;

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }



    public int getPet_id() {
        return pet_id;
    }

    public void setPet_id(int pet_id) {
        this.pet_id = pet_id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public Date getStart_time() {
        return start_time;
    }

    public void setStart_time(Date start_time) {
        this.start_time = start_time;
    }

    public Date getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Date end_time) {
        this.end_time = end_time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public OrderInfo(int phone, int pet_id, int order_id, Date start_time, Date end_time, String status, int employee_id, String service_type, String notes) {
        this.phone = phone;
        this.pet_id = pet_id;
        this.order_id = order_id;
        this.start_time = start_time;
        this.end_time = end_time;
        this.status = status;
        this.employee_id = employee_id;
        this.service_type = service_type;
        this.notes = notes;
    }

    public OrderInfo(int phone, int pet_id, int order_id, String status, int employee_id, String service_type, String notes, String start_time2, String end_time2) {
        this.phone = phone;
        this.pet_id = pet_id;
        this.order_id = order_id;
        this.status = status;
        this.employee_id = employee_id;
        this.service_type = service_type;
        this.notes = notes;
        this.start_time2 = start_time2;
        this.end_time2 = end_time2;
    }
}
