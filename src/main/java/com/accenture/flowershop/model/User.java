package com.accenture.flowershop.model;

public class User {
    public static final int DEFAULT_USER_MONEY_BALANCE = 2000;

    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
    private double moneyBalance;
    private int discount;
    private Role role;

    public User(String firstName, String lastName, String address, String phoneNumber, double moneyBalance, int discount, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.moneyBalance = moneyBalance;
        this.discount = discount;
        this.role = role;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public double getMoneyBalance() {
        return moneyBalance;
    }

    public void setMoneyBalance(double moneyBalance) {
        this.moneyBalance = moneyBalance;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", moneyBalance=" + moneyBalance +
                ", discount=" + discount +
                '}';
    }
}
