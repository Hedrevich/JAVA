package com.leverx.leverxspringproj.domain;

import java.util.List;

public class Flower {
	
	private String flid;
	
	private String name;
	
	public List<Shop> shopList;
	
	public List<Shop> getShopList() {
		return shopList;
	}

	public void setShopList(List<Shop> shopList) {
		this.shopList = shopList;
	}

	public String getName() {
		return name;
	}
	
	public String getFlid() {
		return flid;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void setFlid(String id) {
		this.flid = id;
	}
	
}
