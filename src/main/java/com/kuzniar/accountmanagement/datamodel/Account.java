package com.kuzniar.accountmanagement.datamodel;


import javax.persistence.*;


@Entity
public class Account {

    @Id
    @GeneratedValue
    private Long id;
    private String number;
    @OneToOne
    private Customer owner;
    private double balance;
    private double exchangedBalance;
    private String accountType;

    public Long getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public double getBalance() {
        return balance;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public Customer getOwner() {
        return owner;
    }

    public void setOwner(Customer owner) {
        this.owner = owner;
    }

    public double getExchangedBalance() {
        return exchangedBalance;
    }

    public void setExchangedBalance(double exchangedBalance) {
        this.exchangedBalance = exchangedBalance;
    }

//    @Override
//    public String toString() {
//        return "Your account: " +
//                "\n id= " + id +
//                "\n number= " + number +
//                "\n owner= {" + owner.getId() +
//                "}\n balance= " + balance +
//                "\n accountType= " + accountType;
//    }
}
