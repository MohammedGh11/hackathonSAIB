package com.SAIB.IdeationPlatform.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.SAIB.IdeationPlatform.config.JwtTokenUtil;
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
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	
	public List<User> getAllUser()
	{
		List<User> list=userRepository.findAll();
		return list;
	
		
	}
	
	

	public String addUser(User user , String token)
	{
		String result="";
		token = jwtTokenUtil.removeBearer(token);
		String userType=user.getUserType();
		String email = user.getEmail();
		String adminEmail = jwtTokenUtil.getUsernameFromToken(token);
		User admin = userRepository.findByEmail(adminEmail);
	
	
		
		
//		validate request JWT user type
		if(!admin.getUserType().equalsIgnoreCase("Admin")) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"User does not have the authority");
		}
		if(userRepository.existsByEmail(user.getEmail())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Email already regiesterd in the system");
		}
		
		
		
		if(userRepository.existsByEmail(user.getEmail())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Email already regiesterd in the system");
		}
		
	
		if(userType.equalsIgnoreCase("User")||userType.equalsIgnoreCase("Application Reviewer")||userType.equalsIgnoreCase("Cost Reviewer")||userType.equalsIgnoreCase("Feasbility Reviewer")||userType.equalsIgnoreCase("Admin")) {
			
		}else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"User type Should be one out of the following [User, Application Reviewer, Cost Reviewer ,Feasbility Reviewer , Admin");
		}
		//Password regexp matcher
		if(!user.getPassword().matches(("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[$@–[{}]:;',?/*~$^+=<>]).{8,15}$"))) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"password must match \"^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[$@–[{}]:;',?/*~$^+=<>]).{8,15}$");
		}
		user.setEmail(user.getEmail().toLowerCase());
		user.setPassword(bcryptEncoder.encode(user.getPassword()));
		User storedUser=userRepository.save(user);
		if(storedUser!=null) {
			result=Results.SUCCESS;
		}
		else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"User not created");
		}
		
		return result;
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
	
	public User getUserById(long userId) {
		Optional<User> optional=userRepository.findById(userId);
		
		if(optional.isPresent()) {
			return optional.get();
		}
		else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"User with UserId:"+userId+"doesn't exist");
		}
	}
	
	public String createUser(User user) {
		
		
		String result="";
		//Email is not registered
		if(userRepository.existsByEmail(user.getEmail())) {
					throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Email already regiesterd in the system");
		}
				
		//Password regexp matcher
		if(!user.getPassword().matches(("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[$@–[{}]:;',?/*~$^+=<>]).{8,15}$"))) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"password must match \"^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[$@–[{}]:;',?/*~$^+=<>]).{8,15}$");
		}
		
		user.setEmail(user.getEmail().toLowerCase());
		user.setPassword(bcryptEncoder.encode(user.getPassword()));
		user.setUserType("user");
		User storedUser =  userRepository.save(user);
		if(storedUser!=null) {
			result=Results.SUCCESS;
		}
		else {
			
			
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"User not created");
		}
		
		return result;
	}
	
//util functions not connected to controller


		public long getUserIdByEmail(String email) {
			User user=userRepository.findByEmail(email);
			
			if(user!=null) {
				return user.getUserId();
			}
			else {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No user id exist with id number " + user.getUserId());
			}
		}
	
}
