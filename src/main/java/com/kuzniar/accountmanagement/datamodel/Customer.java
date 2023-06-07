package com.kuzniar.accountmanagement.datamodel;

import javax.persistence.*;

@Entity
public class Customer extends User{

    private String address;
    private String phone;

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
