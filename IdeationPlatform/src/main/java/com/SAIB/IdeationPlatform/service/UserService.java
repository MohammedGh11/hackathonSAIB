package com.SAIB.IdeationPlatform.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import com.SAIB.IdeationPlatform.model.User;
import com.SAIB.IdeationPlatform.repository.UserRepository;
import com.SAIB.IdeationPlatform.util.Results;
import org.springframework.security.crypto.password.PasswordEncoder;
@Service
public class UserService implements UserDetailsService{
	
	@Autowired
	UserRepository userRepository;

	@Autowired
	private PasswordEncoder bcryptEncoder;
	
	
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
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		try {
		User u = userRepository.findByEmail(email);
		
		if (u.getEmail().equals(email)) {
			return new org.springframework.security.core.userdetails.User(u.getEmail(),u.getPassword(), new ArrayList<>());
		} 
		}catch(Exception e) {
			throw new UsernameNotFoundException("User not found with username: " + email);
		}
		return null;
	}
	
	public String createUser(User user) {
		
		String result="";
		user.setPassword(bcryptEncoder.encode(user.getPassword()));
		User storedUser =  userRepository.save(user);
		if(storedUser!=null) {
			result=Results.SUCCESS;
		}
		else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"User not created");
		}
		
		return result;
		



}
}
