package com.kuzniar.accountmanagement.util;


import com.kuzniar.accountmanagement.datamodel.Customer;
import com.kuzniar.accountmanagement.service.CustomerService;

import java.util.Optional;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    CustomerService customerService;

    private static final String EMAIL_PATTERN = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|" +
            "\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|" +
            "\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:" +
            "(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
    private static final String PHONE_NR_PATTERN = "\\+?|\\d+\\d\\s?\\d{0,4}\\-\\d{0,3}\\-\\d{0,3}";

    public Validator() {
        this.customerService = new CustomerService();
    }

    /**
     * method for validating email address
     * if email is incorrect user gets asked for a valid one
     *
     * @param email email address to be validated
     * @return validated email address
     */
    public String validateEmail(String email) {
        Scanner scanner = new Scanner(System.in);
        String correctEmail = email;
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        boolean matches = matcher.find();
        if (matches) {
            System.out.println("Email correct. ");
        } else {
            System.out.println("Email incorrect! Try again");
            correctEmail = scanner.next();
        }

        return correctEmail;
    }

    /**
     * method for validating phone number
     * if phone number is incorrect user gets asked for a valid one
     * @param phone phone number to be validated
     * @return valid phone number
     */
    public String validatePhone(String phone) {
        Scanner scanner = new Scanner(System.in);
        String correctPhone = phone;
        Pattern pattern = Pattern.compile(PHONE_NR_PATTERN);
        Matcher matcher = pattern.matcher(phone);
        boolean matches = matcher.find();
        if (matches) {
            System.out.println("Phone number correct. ");
        } else {
            System.out.println("Phone number incorrect! Try again with pattern +xx xxx-xxx-xxx");
            correctPhone = scanner.next();
        }

        return correctPhone;
    }

    public Customer validateLogin(String login) throws IncorrectUserDataException {
        Optional<Customer> customer = Optional.ofNullable(customerService.getCustomerByLogin(login));
        if (!customer.isPresent()) {
            throw new IncorrectUserDataException("User with login" + login + "not found.");
        }
        return customer.get();
    }

    public void validatePassword(String password, Customer customer) throws IncorrectUserDataException {
        if (customer.getPassword().equals(password)) {
            System.out.println("You are now logged in.");
        } else {
            throw new IncorrectUserDataException("Password incorrect!");
        }
    }
}
