package com.leverx.leverxspringproj.domain;


import java.util.List;

public class Suppliers {

    public Suppliers(int id, String name, List address) {
        this.id = id;
        this.name = name;
        this.address = address;

    }

    private int id;
    private String name;
    private List address;
    private Product product;


    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List getAddress() {
        return address;
    }

    public void setAddress(List address) {
        this.address = address;
    }
}

