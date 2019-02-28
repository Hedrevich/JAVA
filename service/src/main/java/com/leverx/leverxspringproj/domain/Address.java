package com.leverx.leverxspringproj.domain;

public class Address {

    private int addressId;
    private String city;
    private String homeNumber;
    private String streetName;

    public int getAddress_id() {
        return addressId;
    }

    public void setAddress_id(int addressId) {
        this.addressId = addressId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getHomeNumber() {
        return homeNumber;
    }

    public void setHomeNumber(String homeNumber) {
        this.homeNumber = homeNumber;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }
}
