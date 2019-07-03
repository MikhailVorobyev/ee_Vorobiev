package com.accenture.flowershop.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;
@NamedQueries({
        @NamedQuery(name = Order.GET_ALL_SORTED, query = "" +
                "SELECT o FROM Order o JOIN FETCH o.user ORDER BY o.createDate, o.status"),
        @NamedQuery(name = Order.GET_CREATED_ORDERS, query = "" +
                "SELECT o FROM Order o WHERE o.user.login=:userLogin")
})
@Entity
@Table(name = "FLOWERSHOP.ORDERS")
public class Order {

    public static final String GET_ALL_SORTED = "OrderTo.getAll";
    public static final String GET_CREATED_ORDERS = "OrderTo.getUserOrders";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal sum;
    @Column(name = "create_date")
    private String createDate;
    @Column(name = "close_date")
    private String closeDate;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    public Order() {
    }

    public Order(Long id) {
        this.id = id;
    }

    public Order(User user, BigDecimal sum, String createDate, String closeDate, Status status) {
        this.user = user;
        this.sum = sum;
        this.createDate = createDate;
        this.closeDate = closeDate;
        this.status = status;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
