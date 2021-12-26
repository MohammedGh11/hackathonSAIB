package com.SAIB.IdeationPlatform.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import com.SAIB.IdeationPlatform.model.User;
import com.SAIB.IdeationPlatform.repository.UserRepository;
import com.SAIB.IdeationPlatform.util.Results;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	
	public List<User> getAllUser()
	{
		List<User> list=userRepository.findAll();
		return list;
	
		
	}
	
	

	public String addUser(User user)
	{
		String result="";
		User storedUser=userRepository.save(user);
		if(storedUser!=null) {
			result=Results.SUCCESS;
		}
		else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"User not created");
		}
		
		return result;
	}



	public User getUserById(long userId) {
		Optional<User> optional=userRepository.findById(userId);
		
		if(optional.isPresent()) {
			return optional.get();
		}
		else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"User with UserId:"+userId+"doesn't exist");
		}
	}



}
