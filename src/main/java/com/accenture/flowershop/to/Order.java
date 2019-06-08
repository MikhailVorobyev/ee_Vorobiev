package com.accenture.flowershop.to;

public class Order {
    private String name;
    private double price;
    private double sum;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public Order(String name, double price, double sum) {
        this.name = name;
        this.price = price;
        this.sum = sum;
    }
}
