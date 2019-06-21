package com.accenture.flowershop.model;

import java.util.Objects;

public class Order {
    Integer id;
    String userId;
    String userName;
    String userSurname;
    String userAddress;
    String userPhone;
    int sum;
    String createDate;
    String closeDate;
    Status status;
    boolean payed;
    boolean closed;

    public Order(String userId, int sum, String createDate, String closeDate, Status status) {
        this(null, userId, sum, createDate, closeDate, status);
    }

    public Order(Integer id, String userId, int sum, String createDate, String closeDate, Status status) {
        this(id, userId, null, null, null, null, sum, createDate, closeDate, status);

    }

    public Order(Integer id, String userName, String userSurname, String userAddress,
                 String userPhone, int sum, String createDate, String closeDate, Status status) {
        this(id, null, userName, userSurname, userAddress, userPhone, sum, createDate, closeDate, status);

    }

    public Order(Integer id, String userId, String userName, String userSurname, String userAddress,
                 String userPhone, int sum, String createDate, String closeDate, Status status) {
        this.id = id;
        this.userId = userId;
        this.userName = userName;
        this.userSurname = userSurname;
        this.userAddress = userAddress;
        this.userPhone = userPhone;
        this.sum = sum;
        this.createDate = createDate;
        this.closeDate = closeDate;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(String closeDate) {
        this.closeDate = closeDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public boolean isPayed() {
        return payed;
    }

    public void setPayed(boolean payed) {
        this.payed = payed;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id.equals(order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
