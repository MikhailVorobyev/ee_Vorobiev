package com.accenture.flowershop.to;

import com.accenture.flowershop.model.Status;

import java.math.BigDecimal;

public class OrderTo {
    private Long id;
    private UserTo user;
    private BigDecimal sum;
    private String createDate;
    private String closeDate;
    private Status status;
    private boolean payed;
    private boolean closed;

    public OrderTo(Long id, UserTo user, BigDecimal sum, String createDate, String closeDate,
                   Status status, boolean payed, boolean closed) {
        this.id = id;
        this.user = user;
        this.sum = sum;
        this.createDate = createDate;
        this.closeDate = closeDate;
        this.status = status;
        this.payed = payed;
        this.closed = closed;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
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

    public UserTo getUser() {
        return user;
    }

    public void setUser(UserTo user) {
        this.user = user;
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
}
