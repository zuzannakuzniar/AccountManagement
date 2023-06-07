package com.kuzniar.accountmanagement.datamodel;

import javax.persistence.Entity;


@Entity
public class Employee extends User {


    private String position;
    private double salary;


    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }


}
