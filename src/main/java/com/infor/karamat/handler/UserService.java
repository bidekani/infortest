package com.infor.karamat.handler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infor.karamat.model.User;
import com.infor.karamat.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;
	
	public User add(User user) {
		return userRepo.save(user);		
	}
	
	public List<User> listAll(){
		return userRepo.findAll();
	}
	
	public boolean delete(int id) {
		if(userRepo.findById(id).isPresent()) {
				userRepo.deleteById(id);
				return true;
		}else return false;
	}
}
