package com.leverx.leverxspringproj.domain;

import java.math.BigDecimal;
import java.util.GregorianCalendar;
import java.util.List;

public class Product {

    public Product(int id, String name, String description, GregorianCalendar releaseDate, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.releaseDate = releaseDate;
        this.price = price;
    }

    private int id;
    private String name;
    private String description;
    private GregorianCalendar releaseDate;
    private BigDecimal price;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public GregorianCalendar getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(GregorianCalendar releaseDate) {
        this.releaseDate = releaseDate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}