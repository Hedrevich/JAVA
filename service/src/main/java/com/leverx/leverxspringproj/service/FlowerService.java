package com.leverx.leverxspringproj.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service; 
 
import com.leverx.leverxspringproj.dao.FlowerDao;
import com.leverx.leverxspringproj.domain.Flower; 

@Service
public class FlowerService {

	@Autowired
	private FlowerDao FlowerDao;
	
	public List<Flower> getFlowerAll() {
		return FlowerDao.getAll();
	}
	
	public Flower getFlower(String id) {
		Optional<Flower> FlowerOptional = this.FlowerDao.getById(id);
		Flower Flower = null;
		if(FlowerOptional.isPresent()) {
			Flower = FlowerOptional.get();
		}
		return Flower;
	}
	public Flower getFlowerShop(String id) throws SQLException {
	
		return FlowerDao.getShops(id);
	}
	
	public void createFlower(Flower Flower) {
		this.FlowerDao.save(Flower);
	}
	
	public void updateFlower(Flower Flower) {
		this.FlowerDao.update(Flower);
	}
	
	public void deleteFlower(String id) {
		this.FlowerDao.delete(id);
	}
	
}
