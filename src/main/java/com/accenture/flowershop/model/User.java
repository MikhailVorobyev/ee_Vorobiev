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

    public User(String login, String password, String firstName, String lastName, String address,
                String phoneNumber, double moneyBalance, int discount, Role role) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.moneyBalance = moneyBalance;
        this.discount = discount;
        this.role = role;
    }

    public User(String login, double moneyBalance, int discount) {
        this.login = login;
        this.moneyBalance = moneyBalance;
        this.discount = discount;
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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
