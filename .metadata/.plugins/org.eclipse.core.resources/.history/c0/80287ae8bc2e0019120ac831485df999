package com.leverx.leverxspringproj.service;

import java.util.List;
import java.util.Optional; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service; 
 
import com.leverx.leverxspringproj.dao.UserDao;
import com.leverx.leverxspringproj.domain.User; 

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	public List<User> getUserAll() {
		return userDao.getAll();
	}
	
	
	public User getUser(Long id) {
		Optional<User> userOptional = this.userDao.getById(id);
		User user = null;
		if(userOptional.isPresent()) {
			user = userOptional.get();
		}
		return user;
	}
	
	public void createUser(User user) {
		this.userDao.save(user);
	}
	
	public void updateUser(User user) {
		this.userDao.update(user);
	}
	
	public void deleteUser(Long id) {
		this.userDao.delete(id);
	}
	
}
