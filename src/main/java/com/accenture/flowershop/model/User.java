package com.accenture.flowershop.model;

import javax.persistence.*;
import java.util.Set;

@NamedQueries({
        @NamedQuery(name = User.CHECK_TOKEN, query = "" +
                "SELECT u FROM User u WHERE u.login=:login AND u.password=:password"),
        @NamedQuery(name = User.WITHDRAW, query = "UPDATE User u SET u.moneyBalance=:newBalance WHERE u.login=:login"),
})
@Entity
@Table(name = "FLOWERSHOP.USER")
public class User {

    public static final String CHECK_TOKEN = "User.checkToken";
    public static final String WITHDRAW = "User.withdraw";
    //public static final String GET_ALL_ORDERS_SORTED = "User.getAllOrdersSorted";

    public static final int DEFAULT_USER_MONEY_BALANCE = 2000;

    @Id
    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "address")
    private String address;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "money_balance")
    private int moneyBalance;

    @Column(name = "discount")
    private int discount;

    @Column(name = "role")
    private String role;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private Set<Order> orders;

    public User() {
    }

    public User(String login) {
        this.login = login;
    }

    public User(String login, String password, String firstName, String lastName, String address,
                String phoneNumber, int moneyBalance, int discount, String role) {
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

    public User(String login, int moneyBalance, int discount) {
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

    public int getMoneyBalance() {
        return moneyBalance;
    }

    public void setMoneyBalance(int moneyBalance) {
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
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
