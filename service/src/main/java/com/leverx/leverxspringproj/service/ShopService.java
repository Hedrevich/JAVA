package com.leverx.leverxspringproj.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leverx.leverxspringproj.dao.ShopDao;
import com.leverx.leverxspringproj.domain.Shop;

@Service
public class ShopService {
	
	@Autowired
    private ShopDao shopDao;

    public List<Shop> getShopAll() {
        return shopDao.getAll();
    }

    public Shop getShop(String id) {
        Optional<Shop> shopOptional = this.shopDao.getById(id);
        Shop shop = null;
        if (shopOptional.isPresent()) {
            shop = shopOptional.get();
        }
        return shop;
    }

    public void createShop(Shop shop) {
        this.shopDao.save(shop);
    }

    public void updateShop(Shop shop) {
        this.shopDao.update(shop);
    }

    public void deleteShop(String id) {
        this.shopDao.delete(id);
}

}
