package com.example.finall.pojo;

public class Pet {
    public String type;
    public String pet_name;


    public int pet_id;

    public int size;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getOwner_phone() {
        return owner_phone;
    }

    public void setOwner_phone(int owner_phone) {
        this.owner_phone = owner_phone;
    }

    public int owner_phone;

    public String getType() {
        return type;
    }

    public int getPet_id() {
        return pet_id;
    }

    public void setPet_id(int pet_id) {
        this.pet_id = pet_id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPet_name() {
        return pet_name;
    }



    public void setPet_name(String pet_name) {
        this.pet_name = pet_name;
    }

    public Pet(String type, String pet_name, int pet_id, int size, int owner_phone) {
        this.type = type;
        this.pet_name = pet_name;
        this.pet_id = pet_id;
        this.size = size;
        this.owner_phone = owner_phone;
    }
}
