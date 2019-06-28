package com.accenture.flowershop.model;

import javax.persistence.*;

@NamedQueries({
        @NamedQuery(name = Flower.GET_ALL, query = "SELECT f FROM Flower f"),
        @NamedQuery(name = Flower.GET, query = "SELECT f FROM Flower f WHERE f.name=:name"),
})
@Entity
@Table(name = "FLOWERSHOP.FLOWER")
public class Flower {
    public static final String GET = "Flower.get";
    public static final String GET_ALL = "Flower.getAll";

    @Id
    private Integer id;
    private String name;
    private int price;
    private int quantity;

    public Flower() {

    }

    public Flower(Integer id, String name, int price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Flower{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
