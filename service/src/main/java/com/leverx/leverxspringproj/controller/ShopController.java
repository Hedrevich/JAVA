package com.leverx.leverxspringproj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.leverx.leverxspringproj.domain.Shop;
import com.leverx.leverxspringproj.service.ShopService;


@RestController
public class ShopController {

    @Autowired
    private ShopService shopService;

    @GetMapping(value="/Shop")
    public List<Shop> getAllCar() {
        return shopService.getShopAll();
    }

    @GetMapping(value="/Shop/{id}")
    public Shop getShop(@PathVariable String id) {
        return shopService.getShop(id);
    }

    @PostMapping(value="/Shop")
    public void createShop(@RequestBody Shop Shop) {
        shopService.createShop(Shop);
    }

    @DeleteMapping(value="/Shop/{id}")
    public void deleteShop(@PathVariable String id) {
        shopService.deleteShop(id);
    }

    @PutMapping(value="/Shop")
    public void updateShop(@RequestBody Shop Shop) {
        shopService.updateShop(Shop);
    }

}
